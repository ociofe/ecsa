package main.webapp.ociofe.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.webapp.ecsa.hibernate.Jobhistory;
import main.webapp.ecsa.hibernate.Tvseries;
import main.webapp.ecsa.util.SessionFactoryUtil;
import main.webapp.ociofe.model.SeriesUpdate;
import main.webapp.ociofe.model.TVDBUpdates;

import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TheTVDBApi {

	
	
	private static main.webapp.ecsa.util.TvdbParser TvdbParser;
	
	private static final String apiKey = "53756618E9F4E84E";
    private static final String URL_XML = "http://thetvdb.com/api/";
    private static final String URL_BANNER = "http://thetvdb.com/banners/";
    private static final String XML_EXTENSION = ".xml";
    private static final String SERIES_URL = "/series/";
    private static final String EPISODES_URL = "/episodes/";
    private static final String ALL_URL = "/all/";
    public static String lastUpdate = "http://thetvdb.com/api/Updates.php?type=all&time=";
    
    /**
     * Get the series information
     *
     * @param id
     * @param language
     * @return
     * @throws com.omertron.thetvdbapi.TvDbException
     */
//    public static Tvseries getSeries(String id, String language)  {
//        StringBuilder urlBuilder = new StringBuilder();
//        urlBuilder.append(URL_XML);
//        urlBuilder.append(apiKey);
//        urlBuilder.append(SERIES_URL);
//        urlBuilder.append(id);
//        urlBuilder.append("/");
//        if (language != null) {
//            urlBuilder.append(language).append(XML_EXTENSION);
//        }
//
//        
//        List<Tvseries> seriesList = main.webapp.ecsa.util.TvdbParser.getSeriesList(urlBuilder.toString());
//        if (seriesList.isEmpty()) {
//            return null;
//        } else {
//            return seriesList.get(0);
//        }
//    }
    
    public static void saveSeries(String id, String language)  {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(URL_XML);
        urlBuilder.append(apiKey);
        urlBuilder.append(SERIES_URL);
        urlBuilder.append(id);
        urlBuilder.append("/");
        if (language != null) {
            urlBuilder.append(language).append(XML_EXTENSION);
        }

        
        main.webapp.ecsa.util.TvdbParser.getSeriesList(urlBuilder.toString());

    }
    
    public static void saveEpisodes(String id, String language){
        
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(URL_XML);
            urlBuilder.append(apiKey);
            urlBuilder.append(EPISODES_URL);
            urlBuilder.append(id);
            urlBuilder.append("/");
            if (language != null) {
                urlBuilder.append(language).append(XML_EXTENSION);
            }

            main.webapp.ecsa.util.TvdbParser.getEpisode(urlBuilder.toString());
        }
        
    

    
    public static void  getWeeklyUpdates(String lastUpdatatimeURL){
    	
    	//saveSeries("289745","en");
    	
        Document doc = main.webapp.ecsa.util.DOMHelper.getDocumentFromXml(lastUpdatatimeURL);

        if (doc != null) {
        	Element docEle = doc.getDocumentElement();
            NodeList updateNodes = docEle.getChildNodes();
            Jobhistory job = new Jobhistory();
            //Node root = doc.getChildNodes().item(0);
           // NodeList updateNodes = root.getChildNodes();
            Node updateNode;
            int i = 0;
            while ( i < updateNodes.getLength()) {
            	i++;
                if( updateNodes.item(i) != null && updateNodes.item(i).getNodeType()==Node.ELEMENT_NODE){
                	updateNode = updateNodes.item(i);
	                if (updateNode.getNodeName().equals("Series")) {
	                	NodeList seriesID = ((Element) updateNode).getChildNodes();
	                	//seriesID.item(0).getNodeValue();
	                	if(seriesID != null && seriesID.item(0) != null && !seriesID.item(0).getNodeValue().equals("") ){
	                		System.out.println("Series: "+ i + " "+ seriesID.item(0).getNodeValue());
	
	                		//System.out.println("Italiano");
	                		saveSeries(seriesID.item(0).getNodeValue(), "it");
	                		
	                		//Eseguo sempre Inglese alla fine
	                		//System.out.println("Inglese");
	                		saveSeries(seriesID.item(0).getNodeValue(), "en");
	                		
	                	}
	                }else if (updateNode.getNodeName().equals("Episode")){
	                		NodeList episodeID = ((Element) updateNode).getChildNodes();
	                		episodeID.item(0).getNodeValue();
	                    	if(episodeID != null && episodeID.item(0) != null && !episodeID.item(0).getNodeValue().equals("") ){
	                    		System.out.println("Episode: "+ i + " "+ episodeID.item(0).getNodeValue());
	
	                    		//System.out.println("Italiano");
	                    		saveEpisodes(episodeID.item(0).getNodeValue(), "it");
	                    		
	                    		//Eseguo sempre Inglese alla fine
	                    		//System.out.println("Inglese");
	                    		saveEpisodes(episodeID.item(0).getNodeValue(), "en");	
	                    	}
	                	}else if (updateNode.getNodeName().equals("Time")){
	                		NodeList time = ((Element) updateNode).getChildNodes();
	                		System.out.println(time.item(0).getNodeValue());
	                		//mi salvo timestamp ultima esecuzione da utilizzare la volta successiva
	                		if(time != null && time.item(0) != null && time.item(0).getNodeValue() != null){
	                			job.setApiTimestamp(time.item(0).getNodeValue());
	                		}
	                		job.setData(new Date());
	                	}
	                	else if (updateNode.getNodeName().isEmpty()){
	                		i = i-1;
	                		System.out.println("A capo");
	                	}
	                	else{
	                		System.out.println("nodo saltato");
	                	}
	                	
	                }
            }
            //TODO Aggiungere controlli del caso
            //Se il job ha terminato salvo l'ultima esecuzione
            SessionFactoryUtil.saveObject(job, "Jobhistory");
            System.out.println("Fine");

            }

           
        }
    
   
    
}
