package main.webapp.show.util;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import main.webapp.ecsa.hibernate.Jobhistory;
import main.webapp.ecsa.hibernate.Languages;
import main.webapp.ecsa.hibernate.TranslationSeriesname;
import main.webapp.ecsa.hibernate.TranslationSeriesoverview;
import main.webapp.ecsa.hibernate.Tvepisodes;
import main.webapp.ecsa.hibernate.Tvseasons;
import main.webapp.ecsa.hibernate.TvserieUser;
import main.webapp.ecsa.hibernate.Tvseries;
import main.webapp.ecsa.hibernate.Users;
import main.webapp.show.controller.SearchController;

import org.apache.log4j.Level;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.ibm.as400.access.Log;
 
 
public class SessionFactoryUtil {
	 static Logger LOG = Logger.getLogger( SessionFactoryUtil.class.toString());
    private static final SessionFactory sessionFactory;
 
    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure()
            		.buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
        	LOG.log(java.util.logging.Level.SEVERE, "Initial SessionFactory creation failed." + ex);
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
	    	    message.setMessage(e.getMessage());
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
	    		if(User.getPassword().equals(users.get(0).getPassword())){
	    			message.setStatus("SUCCES");
	    			message.setMessage("User Found");
	    			 message.setCode("User Found");
	    		}else{
	    			message.setStatus("FAIL");
	    			message.setMessage("Wrong Password");
	    			message.setCode("Wrong Password");
	    		}
	    	}else{
	    		 message.setStatus("FAIL");
		    	 message.setMessage("User do not exist");
		    	 message.setCode("User do not exist");
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
    	List<Tvepisodes> retu = new ArrayList<Tvepisodes>(); 
    	for(TvserieUser eul: tvserieUserList){
        	
//    		Criteria criteria = session
//    			    .createCriteria(Tvepisodes.class)
//    			    .add( Property.forName("seriesid").eq(Integer.valueOf(eul.getTvserie())) )
//    			    .addOrder(Order.desc("firstAired"));
//    		List<Tvepisodes> temp = (List<Tvepisodes>) criteria.list();
    		String from = "FROM Tvepisodes WHERE seriesid="+ Integer.valueOf(eul.getTvserie().trim())+" ORDER BY firstAired";
    		List<Tvepisodes> temp = session.createQuery(from).list();
    		if(!temp.isEmpty() ){
    			retu.add(temp.get(0));
    		}
    		
    	}

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
	    	
	    	//COntrollo se esiste gi� la stagione per quell'episodio
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
    
    public static List<TranslationSeriesname> searchTranslationSeriesname(String seriesName ){
    	Session session = SessionFactoryUtil.getSessionFactory().openSession();
    	List<TranslationSeriesname> season = null; 
    	Transaction tx = null;
	    try {
	    	tx = session.beginTransaction();
	    	String from = "FROM TranslationSeriesname WHERE translation like '%"+ seriesName +"%' and languageid = 7";
	    	session.createCriteria(TranslationSeriesname.class).setProjection(Projections.distinct(Projections.property("translation")));
	    	season = (List<TranslationSeriesname>) session.createQuery(from).list();
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