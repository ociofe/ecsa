package main.webapp.show.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import main.webapp.ecsa.dao.ElementNoteDao;
import main.webapp.ecsa.hibernate.TranslationSeriesname;
import main.webapp.ecsa.hibernate.Tvepisodes;
import main.webapp.ecsa.hibernate.Tvseasons;
import main.webapp.ecsa.hibernate.TvserieUser;
import main.webapp.ecsa.hibernate.Users;
import main.webapp.show.util.MessageData;
import main.webapp.show.util.SessionFactoryUtil;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/search")
@RestController
public class SearchController {
	
	private String keyWord; 

	 
	 static Logger LOG = Logger.getLogger( SearchController.class.toString());

	
	@RequestMapping(value="/series", method = RequestMethod.GET)
		public @ResponseBody List<Tvseasons> registerGet( Users user, @RequestParam("searchParam") String searchParam) {
	
	 MessageData message = new MessageData();
	 
	 List<Tvseasons> seriesList =  SessionFactoryUtil.seasonSearch(searchParam);
	 return seriesList;
	}
	
	@RequestMapping(value="/series", method = RequestMethod.POST)
	public @ResponseBody List<TranslationSeriesname> searchPost( Users user, @RequestParam("searchParam") String searchParam) {

		MessageData message = new MessageData();
		keyWord = searchParam;
		List<TranslationSeriesname> seriesList =  SessionFactoryUtil.searchTranslationSeriesname(searchParam);
		Collections.sort(seriesList, new TranslationSeriesnameComparator());
		return seriesList;
	}
	
	@RequestMapping(value="/userSeries", method = RequestMethod.POST, consumes="application/json", headers = {"Content-type=application/json"})
	public @ResponseBody List<Tvepisodes> getNextEpisodeForEachSeries(@RequestBody Users user) {

		 
		MessageData message = new MessageData();
 
		List<TvserieUser> seriesList =  SessionFactoryUtil.getSeriesTvserieUserForUser(user);
		if(!seriesList.isEmpty()){
			return SessionFactoryUtil.getLastEpisodeForEachSeries(seriesList);
		}
		return null;
	}
 
	 public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

}

class TranslationSeriesnameComparator implements Comparator<TranslationSeriesname>
{
	 static Logger LOG = Logger.getLogger( TranslationSeriesnameComparator.class.toString());
	 
    public int compare(TranslationSeriesname c1, TranslationSeriesname c2)
    {
    	 ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

    	 SearchController keyWord = (SearchController) context.getBean("searchController");
         
    	 LOG.info(keyWord.getKeyWord());
        return c1.getTranslation().compareTo(c2.getTranslation());
    }
}
