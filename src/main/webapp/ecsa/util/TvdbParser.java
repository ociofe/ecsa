package main.webapp.ecsa.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceException;

import main.webapp.ecsa.hibernate.Languages;
import main.webapp.ecsa.hibernate.TranslationEpisodename;
import main.webapp.ecsa.hibernate.TranslationEpisodeoverview;
import main.webapp.ecsa.hibernate.TranslationSeriesname;
import main.webapp.ecsa.hibernate.TranslationSeriesoverview;
import main.webapp.ecsa.hibernate.Tvepisodes;
import main.webapp.ecsa.hibernate.Tvseasons;
import main.webapp.ecsa.hibernate.Tvseries;
import main.webapp.ociofe.model.EpisodeUpdate;
import main.webapp.ociofe.model.SeriesUpdate;
import main.webapp.ociofe.model.TVDBUpdates;

import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TvdbParser {

	@Resource
	private static DOMHelper DOMHelper;
	
	
	private static final String TYPE_BANNER = "banner";
    private static final String TYPE_FANART = "fanart";
    private static final String TYPE_POSTER = "poster";
    private static final String BANNER_PATH = "BannerPath";
    private static final String VIGNETTE_PATH = "VignettePath";
    private static final String THUMBNAIL_PATH = "ThumbnailPath";
    // The anticipated largest episode number
    private static final int MAX_EPISODE = 24;
    // Error messages
    private static final String ERROR_GET_XML = "Failed to get XML document from URL";
    private static final String ERROR_RETRIEVE_EPISODE_INFO = "Unable to retrieve episode information from TheTVDb, try again later.";
    private static final String ERROR_NOT_ALLOWED_IN_PROLOG = "content is not allowed in prolog";
    // Constants
    private static final int ERROR_MSG_EPISODE = 3;
    private static final int ERROR_MSG_SEASON = 2;
    private static final int ERROR_MSG_SERIES = 1;
    private static final int ERROR_MSG_GROUP_COUNT = 3;
    // Literals
    private static final String SERIES = "Series";
    private static final String TIME = "time";
    private static final String EPISODE = "Episode";
    private static final String BANNER = "Banner";
    private static final String LAST_UPDATED = "lastupdated";
    private static final String OVERVIEW = "Overview";
    private static final String IMDB_ID = "IMDB_ID";
    private static final String FIRST_AIRED = "FirstAired";
    private static final String SERIES_NAME = "SeriesName";
    private static final String RATING = "Rating";
    private static final String LANGUAGE = "Language";
	
    public static List<Tvseries> getSeriesList(String urlString)  {
        List<Tvseries> seriesList = new ArrayList<Tvseries>();
        Tvseries series;
        NodeList nlSeries;
        Node nSeries;
        Element eSeries;

        Document doc = main.webapp.ecsa.util.DOMHelper.getDocumentFromXml(urlString);

        if (doc != null) {
            nlSeries = doc.getElementsByTagName(SERIES);
            for (int loop = 0; loop < nlSeries.getLength(); loop++) {
                nSeries = nlSeries.item(loop);
                if (nSeries.getNodeType() == Node.ELEMENT_NODE) {
                    eSeries = (Element) nSeries;
                    series = parseNextSeries(eSeries);
                    if (series != null) {
                        seriesList.add(series);
                    }
                }
            }
        }

        return seriesList;
    }
    
    public static Tvseries parseNextSeries(Element eSeries) {
    	if(!getValueFromElement(eSeries, SERIES_NAME).isEmpty()){
	    	Tvseries series = new Tvseries();
	    	String lg = getValueFromElement(eSeries, "Language");
	    	//aggiorno il generale solo se la lingua è inglese!
	    	
	    	/* CREATING TV SERIES */
	        series.setId(Integer.valueOf((getValueFromElement(eSeries, "id"))));
	        series.setActors(getValueFromElement(eSeries, "Actors"));
	        series.setAirsDayOfWeek(getValueFromElement(eSeries, "Airs_DayOfWeek"));
	        series.setAirsTime(getValueFromElement(eSeries, "Airs_Time"));
	        series.setRating(getValueFromElement(eSeries, "ContentRating"));
	        series.setFirstAired(getValueFromElement(eSeries, FIRST_AIRED));
	        series.setGenre(getValueFromElement(eSeries, "Genre"));
	        series.setImdbId((getValueFromElement(eSeries, "IMDB_ID") != "") ? getValueFromElement(eSeries, "IMDB_ID") : null);
	        series.setNetwork(getValueFromElement(eSeries, "Network"));
	        series.setOverview(getValueFromElement(eSeries, OVERVIEW));
	        series.setRating(getValueFromElement(eSeries, RATING));
	        series.setRuntime(getValueFromElement(eSeries, "Runtime"));
	        series.setSeriesId((getValueFromElement(eSeries, "SeriesID") != "") ? getValueFromElement(eSeries, "SeriesID") : null);
	        series.setSeriesName(getValueFromElement(eSeries, SERIES_NAME));
	        series.setStatus(getValueFromElement(eSeries, "Status"));
	        //series.setMirrorupdate(new Date()); Rimosso perchè causava problemi alla save!
	        series.setRequestcomment(" ");
	        series.setLocked(" ");
	        series.setDisabled(" ");
	        series.setLastupdated(Integer.valueOf(getValueFromElement(eSeries, LAST_UPDATED)));
	        series.setZap2itId((getValueFromElement(eSeries, "zap2it_id") != "") ? getValueFromElement(eSeries, "zap2it_id") : null );
	    	System.out.println(series.getId());
	    	SessionFactoryUtil.saveObject(series,"Tvseries");
	    	
	        
	
	    	
	    	
	       ///  SALVO LE TRADUZIONI 
	    	
	      /* CREATING TV SERIES TITLE  */
	      
	      TranslationSeriesname seriesName = new TranslationSeriesname();
	      seriesName.setMirrorupdate(new Date());
	      seriesName.setSeriesid(Integer.parseInt(getValueFromElement(eSeries, "id")));
	      seriesName.setTranslation(getValueFromElement(eSeries, SERIES_NAME));
	      
	      //Extract language code
	      List<Languages> languge = SessionFactoryUtil.retriveLanguages(lg);
	      seriesName.setLanguageid(languge.get(0).getId());
	      
	      //check if alredy exist ad if delete record
	      SessionFactoryUtil.ceckAndRemove("TranslationSeriesname",seriesName.getSeriesid(),seriesName.getLanguageid());
	      //Save 
	      SessionFactoryUtil.saveObject(seriesName,"TranslationSeriesname");
	      
	      
	      /* CREATING TV SERIES OVERVIEW */
	      
	      TranslationSeriesoverview seriesoverview = new TranslationSeriesoverview();
	      seriesoverview.setMirrorupdate(new Date());
	      seriesoverview.setSeriesid(Integer.parseInt(getValueFromElement(eSeries, "id")));
	      
	      seriesoverview.setTranslation(getValueFromElement(eSeries, OVERVIEW));
	      //Extract language code
	      languge = SessionFactoryUtil.retriveLanguages(lg);
	      seriesoverview.setLanguageid(languge.get(0).getId());
	      
	      //check if alredy exist ad if delete record
	      SessionFactoryUtil.ceckAndRemove("TranslationSeriesoverview",seriesoverview.getSeriesid(),seriesoverview.getLanguageid());
	      //Save 
	      SessionFactoryUtil.saveObject(seriesoverview,"TranslationSeriesoverview");
	      
	    	
	      return series;
	    }
    	System.out.println("NoName");
    	return null;
    }
    
    
    private static List<String> parseList(String input, String delim) {
        List<String> result = new ArrayList<String>();

        StringTokenizer st = new StringTokenizer(input, delim);
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            if (token.length() > 0) {
                result.add(token);
            }
        }

        return result;
    }


    public static Tvepisodes getEpisode(String urlString) {
    	Tvepisodes episode = new Tvepisodes();
        NodeList nlEpisode;
        Node nEpisode;
        Element eEpisode;

        Document doc = main.webapp.ecsa.util.DOMHelper.getDocumentFromXml(urlString);

        if (doc != null) {
            nlEpisode = doc.getElementsByTagName(EPISODE);
            for (int loop = 0; loop < nlEpisode.getLength(); loop++) {
                nEpisode = nlEpisode.item(loop);
                if (nEpisode.getNodeType() == Node.ELEMENT_NODE) {
                    eEpisode = (Element) nEpisode;
                    episode = parseNextEpisode(eEpisode);
                    if (episode != null) {
                        // We only need the first episode
                        break;
                    }
                }
            }
        }

        return episode;

    }

    /**
     * Parse the document for episode information
     *
     * @param doc
     * @throws Throwable
     */
    public static Tvepisodes parseNextEpisode(Element eEpisode) {
        Tvepisodes episode = new Tvepisodes();
        Tvseasons season = new Tvseasons();
        
        //Creo Episodio
        episode.setId(Integer.valueOf((getValueFromElement(eEpisode, "id"))));
        episode.setSeasonid(Integer.valueOf(getValueFromElement(eEpisode, "seasonid")));
        episode.setDvdEpisodenumber(BigDecimal.valueOf(Double.valueOf((getValueFromElement(eEpisode, "DVD_episodenumber") !="" ? getValueFromElement(eEpisode, "DVD_episodenumber") : "0")))); 
        episode.setFirstAired(getValueFromElement(eEpisode, FIRST_AIRED));
        episode.setGuestStars(getValueFromElement(eEpisode, "GuestStars"));
        episode.setDirector(getValueFromElement(eEpisode, "Director"));
        episode.setWriter(getValueFromElement(eEpisode, "Writer"));
        episode.setOverview(getValueFromElement(eEpisode, OVERVIEW));
        episode.setProductionCode(getValueFromElement(eEpisode, "ProductionCode"));
        episode.setLastupdated(Integer.valueOf((getValueFromElement(eEpisode, "LAST_UPDATED") !="" ? getValueFromElement(eEpisode, "LAST_UPDATED") : "0")));
        episode.setFlagged(Integer.valueOf((getValueFromElement(eEpisode, "flagged") !="" ? getValueFromElement(eEpisode, "flagged") : "0")));
        episode.setDvdDiscid(getValueFromElement(eEpisode, "DVD_discid"));
        episode.setDvdSeason(Integer.valueOf((getValueFromElement(eEpisode, "DVD_season") !="" ? getValueFromElement(eEpisode, "DVD_season") : "0")));
        episode.setEpisodeNumber(getEpisodeValue(eEpisode, "EpisodeNumber"));
        episode.setDvdChapter(Integer.valueOf((getValueFromElement(eEpisode, "DVD_chapter") !="" ? getValueFromElement(eEpisode, "DVD_chapter") : "0")));
        episode.setSeriesid(Integer.valueOf(getValueFromElement(eEpisode, "seriesid")));
        episode.setEpisodeName(getValueFromElement(eEpisode, "EpisodeName"));
        episode.setImdbId(getValueFromElement(eEpisode, IMDB_ID));
        episode.setThumbAuthor(0);
        //Creo stagione
        season.setId(Integer.valueOf((getValueFromElement(eEpisode, "seasonid"))));
        season.setSeriesid(Integer.valueOf(getValueFromElement(eEpisode, "seriesid")));
        season.setSeason(getEpisodeValue(eEpisode, "SeasonNumber"));
        season.setLocked("0");
        //season.setMirrorupdate(new Date());
        
        SessionFactoryUtil.saveObject(season, "Tvseasons");
        SessionFactoryUtil.saveObject(episode, "Tvepisodes");
        
        ///  SALVO LE TRADUZIONI 
        String lg = getValueFromElement(eEpisode, "Language");
		  /* CREATING TV episode TITLE  */
		  
		  TranslationEpisodename episodeName = new TranslationEpisodename();
		  episodeName.setMirrorupdate(new Date());
		  episodeName.setEpisodeid(Integer.parseInt(getValueFromElement(eEpisode, "id")));
		  episodeName.setTranslation(getValueFromElement(eEpisode, "EpisodeName"));
		  
		  //Extract language code
		  List<Languages> languge = SessionFactoryUtil.retriveLanguages(lg);
		  episodeName.setLanguageid(languge.get(0).getId());
		  
		  
		  //check if alredy exist ad if delete record
		  SessionFactoryUtil.ceckAndRemove("TranslationEpisodename",episodeName.getEpisodeid(),episodeName.getLanguageid());
		  //Save 
		  SessionFactoryUtil.saveObject(episodeName,"TranslationEpisodename");
	      
	      
	      /* CREATING TV episode OVERVIEW */
	      
	      TranslationEpisodeoverview episodeoverview = new TranslationEpisodeoverview();
	      episodeoverview.setMirrorupdate(new Date());
	      episodeoverview.setEpisodeid(Integer.parseInt(getValueFromElement(eEpisode, "id")));
	      episodeoverview.setTranslation(getValueFromElement(eEpisode, OVERVIEW));
	      
	      //Extract language code
	      languge = SessionFactoryUtil.retriveLanguages(lg);
	      episodeoverview.setLanguageid(languge.get(0).getId());
	      
	      //check if alredy exist ad if delete record
	      SessionFactoryUtil.ceckAndRemove("TranslationEpisodeoverview",episodeoverview.getEpisodeid() ,episodeoverview.getLanguageid());
	      //Save 
	      SessionFactoryUtil.saveObject(episodeoverview,"TranslationEpisodeoverview");
	      
        //TODO gestire lingua
        //episode.setLanguage(getValueFromElement(eEpisode, LANGUAGE));
        
        //TODO decidere come gestire rating - momentaneamente non gestito
        //episode.setRating(Integer.valueOf(getValueFromElement(eEpisode, RATING)));

       // episode.setAbsoluteNumber(getValueFromElement(eEpisode, "absolute_number"));
        
        
        //TODO check or add tv seasons
        
//        int idSeason = SessionFactoryUtil.checkSeasonExist(getEpisodeValue(eEpisode, "SeasonNumber"), episode.getSeriesid());
//        if(idSeason != -1){
//        	episode.setSeasonid(idSeason);
//        }else{
//        	Tvseasons season = new Tvseasons();
//        	season.setLockedby(0);
//        	season.setSeason(getEpisodeValue(eEpisode, "SeasonNumber"));
//        	season.setSeriesid(episode.getSeriesid());
//        	SessionFactoryUtil.saveObject(season, "Tvseasons");	
//        }
//        //a questo punto dovrei trovare la stagione, estraggo l'indirizzo e salvo l'episodio
//        idSeason = SessionFactoryUtil.checkSeasonExist(getEpisodeValue(eEpisode, "SeasonNumber"), episode.getSeriesid());
//        if(idSeason != -1){
//        	System.out.println("CAZZO DI BUDDA");
//        }else{
//        	episode.setSeasonid(idSeason);
//        	SessionFactoryUtil.saveObject(episode, "Tvepisodes");	
//        }
//        
        
        	
        //episode.setSeasonNumber(getEpisodeValue(eEpisode, "SeasonNumber"));
        
        return episode;
    }
    
    

    /**
     * Process the "key" from the element into an integer.
     *
     * @param eEpisode
     * @param key
     * @return the value, 0 if not found or an error.
     */
    private static int getEpisodeValue(Element eEpisode, String key) {
        int episodeValue;
        try {
            String value = DOMHelper.getValueFromElement(eEpisode, key);
            episodeValue = Integer.valueOf(value);
        } catch (WebServiceException ex) {
            //LOG.trace("Failed to read episode value", ex);
            episodeValue = 0;
        } catch (NumberFormatException ex){
        	//LOG.trace("Failed to read episode value", ex);
        	episodeValue = 0;
        }

        return episodeValue;
    }
    
    /**
     * Gets the string value of the tag element name passed
     *
     * @param element
     * @param tagName
     * @return
     */
    public static String getValueFromElement(Element element, String tagName) {
        NodeList elementNodeList = element.getElementsByTagName(tagName);
        if (elementNodeList == null) {
            return "";
        } else {
            Element tagElement = (Element) elementNodeList.item(0);
            if (tagElement == null) {
                return "";
            }

            NodeList tagNodeList = tagElement.getChildNodes();
            if (tagNodeList == null || tagNodeList.getLength() == 0) {
                return "";
            }
            return tagNodeList.item(0).getNodeValue();
        }
    }
    
    
   

	public static DOMHelper getDOMHelper() {
		return DOMHelper;
	}

	public static void setDOMHelper(DOMHelper dOMHelper) {
		DOMHelper = dOMHelper;
	}
}
