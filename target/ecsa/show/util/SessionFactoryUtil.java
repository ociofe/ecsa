package main.webapp.show.util;


import java.util.List;

import main.webapp.ecsa.hibernate.Jobhistory;
import main.webapp.ecsa.hibernate.Languages;
import main.webapp.ecsa.hibernate.TranslationSeriesname;
import main.webapp.ecsa.hibernate.TranslationSeriesoverview;
import main.webapp.ecsa.hibernate.Tvepisodes;
import main.webapp.ecsa.hibernate.Tvseasons;
import main.webapp.ecsa.hibernate.TvserieUser;
import main.webapp.ecsa.hibernate.Tvseries;
import main.webapp.ecsa.hibernate.Users;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
 
 
public class SessionFactoryUtil {
 
    private static final SessionFactory sessionFactory;
 
    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure()
            		.buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static MessageData saveObject(Object obj, String entity){
    	
    	Session session = SessionFactoryUtil.getSessionFactory().openSession();
    	MessageData message = new MessageData();
    	Transaction tx = null;
	    try {
	    	tx = session.beginTransaction();
	    	session.isConnected();
	    	session.isOpen();
	    	session.saveOrUpdate(entity,obj);
	    	 tx.commit();
	    	 message.setStatus("SUCCES");
	    	  
	    	} catch(Exception e) {
	    	  if (tx != null) {
	    	    tx.rollback();
	    	    message.setStatus("FAIL");
	    	    message.setStatus(e.getMessage());
	    	    message.setCode(e.getCause().getLocalizedMessage());
	    	    
	    	  }
	    	} finally {
	    	  if (session != null) {
	    		  session.close(); // 
	    		  return message;
	    	  }
	    	}
	    return message;
	    
    }
    
    public static MessageData userExist(Users User){
    	
    	Session session = SessionFactoryUtil.getSessionFactory().openSession();
    	MessageData message = new MessageData();
    	Transaction tx = null;
	    try {
	    	tx = session.beginTransaction();
	    	session.isConnected();
	    	session.isOpen();
	    	String query = "FROM Users WHERE MAIL = '"+User.getMail()+"'";
	    	List<Users> users = session.createQuery(query).list();
	    	if (!users.isEmpty()){
	    		message.setStatus("SUCCES");
	    		message.setMessage("User Found");
	    	}
	    	} catch(Exception e) {
	    	  if (tx != null) {
	    	    tx.rollback();
	    	    message.setStatus("FAIL");
	    	    message.setStatus(e.getMessage());
	    	    message.setCode(e.getLocalizedMessage());
	    	  }
	    	} finally {
	    	  if (session != null) {
	    	    	session.clear();
	    		    session.close();
	    	  }
	    	}
	    return message;
	    
    }
    
    public static void ceckAndRemove(String what, int id, int languageID){
    	Session session = SessionFactoryUtil.getSessionFactory().openSession();
    	Transaction tx = session.beginTransaction();
    	if (what.equals("TranslationSeriesname")){
    		String from = "FROM TranslationSeriesname WHERE languageid= "+ languageID +" AND seriesid= "+id ;
    		List<TranslationSeriesname> del = session.createQuery(from).list();
    		while(!del.isEmpty()){
    			session.delete(del.get(0));
    			del.remove(0);
    		    
    		}
    	}else if(what.equals("TranslationSeriesoverview")){
    		String from = "FROM TranslationSeriesoverview WHERE languageid= "+ languageID +" AND seriesid= "+id ;
    		List<TranslationSeriesoverview> del = session.createQuery(from).list();
    		while(!del.isEmpty()){
    			session.delete(del.get(0));
    			del.remove(0);
    		    
    		}
    	}else if(what.equals("TranslationEpisodename")){
    		String from = "FROM TranslationEpisodename WHERE languageid= "+ languageID +" AND episodeid= "+id ;
    		List<TranslationSeriesoverview> del = session.createQuery(from).list();
    		while(!del.isEmpty()){
    			session.delete(del.get(0));
    			del.remove(0);
    		    
    		}
    	}else if(what.equals("TranslationEpisodeoverview")){
    		String from = "FROM TranslationEpisodeoverview WHERE languageid= "+ languageID +" AND episodeid= "+id ;
    		List<TranslationSeriesoverview> del = session.createQuery(from).list();
    		while(!del.isEmpty()){
    			session.delete(del.get(0));
    			del.remove(0);
    		    
    		}
    	}
    	
    	session.clear();
	    tx.commit();
	    session.close();
    }
    
    public static List<TvserieUser> getSeriesTvserieUserForUser(Users user){
    	
    	Session session = SessionFactoryUtil.getSessionFactory().openSession();
    	List<TvserieUser> retu = null; 
    	String from = "FROM TvserieUser WHERE User="+ user.getUser()+"";
		retu = session.createQuery(from).list();
	    session.beginTransaction();
	    session.flush();   
	    session.close();
	    return retu;
		    
    }
    
    public static List<Tvepisodes> getLastEpisodeForEachSeries(List<TvserieUser> tvserieUserList){
    	
    	Session session = SessionFactoryUtil.getSessionFactory().openSession();
    	List<Tvepisodes> retu = null; 
   
    	
    	for(TvserieUser eul: tvserieUserList){
        	Criteria crit = session.createCriteria(Tvepisodes.class);
        	crit.setMaxResults(1);
        	crit.addOrder( Order.desc("firstAired") );
        	crit.add( Restrictions.eq("seriesid", eul.getTvserie()));
        	crit.uniqueResult();
    		retu.addAll( crit.list());
    	}
//    	episodeList = episodeList.substring(0, episodeList.length()-1);
//    	episodeList = episodeList.concat(") ");
//    	String from = "FROM Tvepisodes WHERE Seriesid IN"+ episodeList+ "and ";
//		retu = session.createQuery(from).list();
	    session.beginTransaction();
	    session.flush();   
	    session.close();
	   
	    return retu;
		    
    }
    
    public static List<Languages> retriveLanguages(String language){
    	
    	Session session = SessionFactoryUtil.getSessionFactory().openSession();
    	List<Languages> retu = null; 
    	String from = "FROM Languages WHERE abbreviation='"+ language.concat("'");
		retu = session.createQuery(from).list();
	    session.beginTransaction();
	    session.flush();   
	    session.close();
	   
	    return retu;
		    
    }
    
    public static Jobhistory retriveLastSuccesJob(){
    	
    	Session session = SessionFactoryUtil.getSessionFactory().openSession();
    	List<Jobhistory> retu = null; 
    	String from = "FROM Jobhistory order by timestamp desc";
		retu = session.createQuery(from).list();
	    session.beginTransaction();
	    session.flush();   
	    session.close();
	    if(retu != null){
	    	return retu.get(0);
	    }
	    return null;
	    
		    
    }
    
    
    public static int checkSeasonExist(int seasonNumber, int seriesId ){
    	int seasonId = -1;
    	Session session = SessionFactoryUtil.getSessionFactory().openSession();
    	List<Tvseasons> season = null; 
    	Transaction tx = null;
	    try {
	    	tx = session.beginTransaction();
	    	String from = "FROM Tvseasons WHERE season= "+ seasonNumber +" seriesid= "+seriesId;
	    	season = session.createQuery(from).list();
	    	tx.commit();
	    	
	    	//COntrollo se esiste già la stagione per quell'episodio
	    	if(!season.isEmpty()){
	    		seasonId = season.get(0).getId();
	    	}
	    	
	    	  
	    	} catch(Exception e) {
	    	  if (tx != null) {
	    	    tx.rollback();
	    	  }
	    	} finally {
	    	  if (session != null) {
	    		  session.close(); // 
	    	  }
	    	}
	    
	    return seasonId;
    }
    
    public static List<Tvseasons> seasonSearch(String seriesName ){
    	Session session = SessionFactoryUtil.getSessionFactory().openSession();
    	List<Tvseasons> season = null; 
    	Transaction tx = null;
	    try {
	    	tx = session.beginTransaction();
	    	String from = "FROM Tvseries WHERE seriesName like '%"+ seriesName +"%'";
	    	season = session.createQuery(from).list();
	    	tx.commit();
	    	} catch(Exception e) {
	    	  if (tx != null) {
	    	    tx.rollback();
	    	  }
	    	} finally {
	    	  if (session != null) {
	    		  session.close(); // 
	    	  }
	    	}
	    
	    return season;
    }
    
 
}