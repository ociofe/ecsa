package test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import main.webapp.show.util.HttpUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SpringHibernateMain {
	
//	@Value("${tvdb.api.key}")
//    private String TVDBKEY;
//	
//	@Value("${tvdb.main.mirror}")
//    private String TVDBMAINMIRROR;
//	
	
	private static HttpUtil httpUtil;
	
	
    private static final String LANGUAGE_ENGLISH = "en";
    private static final String TVDBID = "80348";
    private static final String SERIES_NAME = "Chuck";
    private static final String EPISODE_ID = "1534661";
    private static final String SEASON_ID = "27984";
    private static final String SEASON_YEAR = "2007";


	public static void main(String[] args) {
		 
			String url="http://thetvdb.com/api/Updates.php?type=none";
			
			

				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder;
				
				try {
					dBuilder = dbFactory.newDocumentBuilder();
					String resp= HttpUtil.getXMLDocument(url);
					Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(resp.getBytes("UTF-8"))));
					doc.getDocumentElement().normalize();
					
					System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					
					NodeList nList = doc.getElementsByTagName("Items");
					
					System.out.println("----------------------------");
				 
					for (int temp = 0; temp < nList.getLength(); temp++) {
				 
						Node nNode = nList.item(temp);
				 
						System.out.println("\nCurrent Element :" + nNode.getNodeName());
				 
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				 
							Element eElement = (Element) nNode;
				 
							System.out.println("Staff id : " + eElement.getAttribute("Time"));
							
						}
					}
					
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			 
				//optional, but recommended
				//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
				
				
				
				

			
			//hibernate
	       // Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();

//	        session.beginTransaction();
//	        AkaSeriesname provvaserie = new	AkaSeriesname();
//	        provvaserie.setSeriesid(BigDecimal.valueOf((new Random()).nextInt()));
//	        provvaserie.setName("MiaSerie");
//	        provvaserie.setLastedit(new Date());
//	        session.save(provvaserie);
	        
	        //create and get mirror

	        

	        //context.close();    
	    }



	public static HttpUtil getHttpUtil() {
		return httpUtil;
	}



	public static void setHttpUtil(HttpUtil httpUtil) {
		SpringHibernateMain.httpUtil = httpUtil;
	}
	


}
