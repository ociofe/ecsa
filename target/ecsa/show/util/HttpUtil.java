package main.webapp.show.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class HttpUtil {
	
	
	public static String getXMLDocument(String url) {
		String response="";
		HttpGet httpGetRequest = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();
		
			
		HttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(httpGetRequest);
			HttpEntity resEntity = httpResponse.getEntity();
			response = EntityUtils.toString(resEntity);
			httpClient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}
	
	public static Document getDocumentFromXml(String url) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		HttpGet httpGetRequest = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();
		Document doc= null;
			
		HttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(httpGetRequest);
			HttpEntity resEntity = httpResponse.getEntity();
			String response = EntityUtils.toString(resEntity);
			httpClient.getConnectionManager().shutdown();

			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(response.getBytes("UTF-8"))));
			doc.getDocumentElement().normalize();
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return doc;
	}
	
	

	
}
