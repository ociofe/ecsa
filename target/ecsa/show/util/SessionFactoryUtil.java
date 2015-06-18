package main.webapp.show.util;


import java.util.List;

import main.webapp.ecsa.hibernate.Jobhistory;
import main.webapp.ecsa.hibernate.Languages;
import main.webapp.ecsa.hibernate.TranslationSeriesname;
import main.webapp.ecsa.hibernate.TranslationSeriesoverview;
import main.webapp.ecsa.hibernate.Tvseasons;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
 
 
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
    
    public static void saveObject(Object obj, String entity){
    	
    	Session session = SessionFactoryUtil.getSessionFactory().openSession();
    	
    	Transaction tx = null;
	    try {
	    	tx = session.beginTransaction();
	    	session.saveOrUpdate(entity,obj);
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
    
 
}