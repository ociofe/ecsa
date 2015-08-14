package main.webapp.ociofe.job;

import main.webapp.ecsa.hibernate.Jobhistory;
import main.webapp.ecsa.hibernate.Tvseries;
import main.webapp.ociofe.api.TheTVDBApi;
import main.webapp.show.util.SessionFactoryUtil;

import org.hibernate.Session;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;



public class UpdateJobEpisodeSeries {
	
	private static TheTVDBApi theTVDBApi;
	
	//"http://thetvdb.com/api/53756618E9F4E84E/series/80348/en.xml
	public static String lastUpdate = "http://thetvdb.com/api/Updates.php?type=all&time=";
	

	public static void main(String[] args) {
		
		
			//TODO: estraggo la lista di tutte le serie e tutti gli episodi da aggiornare
			String url = "http://thetvdb.com/api/Updates.php?type=all&time=1423825658";
			Jobhistory job = SessionFactoryUtil.retriveLastSuccesJob();
			if(job != null && !job.getApiTimestamp().isEmpty()){
				 url = lastUpdate.concat(job.getApiTimestamp());
			}
			
			TheTVDBApi.getWeeklyUpdates(url);	
	    }
	
	public static void run() {
		
		//TODO: estraggo la lista di tutte le serie e tutti gli episodi da aggiornare
		String url = "http://thetvdb.com/api/Updates.php?type=all&time=1423825658";
		Jobhistory job = SessionFactoryUtil.retriveLastSuccesJob();
		if(job != null && !job.getApiTimestamp().isEmpty()){
			 url = lastUpdate.concat(job.getApiTimestamp());
		}
		
		TheTVDBApi.getWeeklyUpdates(url);
    	
//		Tvseries ser = TheTVDBApi.getSeries("279121","it");
//    	//hibernate save
//	    Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
//	    session.beginTransaction();
//	    session.saveOrUpdate(ser);
//	    session.flush();   
//	    session.close();

    	
    }

	
	
	 public TheTVDBApi getTheTVDBApi() {
		return theTVDBApi;
	}

	public void setTheTVDBApi(TheTVDBApi theTVDBApi) {
		UpdateJobEpisodeSeries.theTVDBApi = theTVDBApi;
	}

}
