package main.webapp.ecsa.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import main.webapp.ecsa.dao.ElementNote;
import main.webapp.ecsa.dao.Locazione;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csvreader.CsvWriter;
import com.ibm.as400.util.commtrace.Data;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.Barcode;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.Barcode39;
import com.lowagie.text.pdf.BarcodeCodabar;
import com.lowagie.text.pdf.BarcodeEAN;
import com.lowagie.text.pdf.BarcodePDF417;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;


@Component
@Controller
//@RequestMapping("/")
public class ConteController {
	
	@Value("${as400.c1.driver}")
    private String C1DRIVER;
	
	@Value("${as400.c1.url}")
    private String C1URL;
	
	@Value("${as400.ch.url}")
    private String CHURL;
	
	@Value("${as400.c1.pwd}")
	private String C1PWD;
	
	@Value("${attachment.folder}")
	private String BARCODEFOLDER;
	
	@Value("${attachment.folder.history}")
	private String BARCODEFOLDERHISTORY;
	

	@RequestMapping(value="/locazioni", method = RequestMethod.GET)
	public String welcomeLocazioni(ModelMap model) {
		
		Locazione locazione = new Locazione();
		model.addAttribute("locazione", locazione);
		
		return "locazioni";
		
	}		
	
	@RequestMapping(value="/locazioni/insert", method = RequestMethod.POST)
	public String insertLocazione(ModelMap model,HttpServletRequest request, @ModelAttribute("locazione")Locazione locazione) {
		
		//estraggo fornitore
		String fornitore =locazione.getCodFornitore();
		//fornitore=fornitore.concat("A2");
		int risultato = creaLocazioneTemporanea(fornitore, locazione.getC1orCH(), locazione.getMagazzino(), locazione.getZona() );
		
		if(risultato > 0){
			model.addAttribute("risultato", "OK: Locazione inserita correttamente");
		}
		else{
			model.addAttribute("risultato", "ERRORE: Locazione non inserita");
		}
		if(risultato == -100){
			model.addAttribute("risultato", "ERRORE: "
					+ "Già presente questo fornitore nella tabella WHOLOC");
		}
		
		

		try {
			// step 1
	        Document document = new Document(new Rectangle(340, 842));
	        // step 2
	        PdfWriter writer;
	        new File(BARCODEFOLDER).mkdirs();
	        //String path="\\\\ecsa-fs\\data$\\Pubblico\\BarcodeApplicazione";
	        new File(BARCODEFOLDER).mkdirs(); 
	        writer = PdfWriter.getInstance(document, new FileOutputStream(BARCODEFOLDER.concat("\\barcode").concat(fornitore).concat(".pdf")));
	        // step 3
	        document.open();
	        // step 4
	        PdfContentByte cb = writer.getDirectContent();
	        
	       
	        Barcode39 code39 = new Barcode39();
	        code39.setCode(locazione.getZona()+fornitore);
	        code39.setBarHeight(20f);
	        code39.setX(1.5f);
	        document.add(code39.createImageWithBarcode(cb, null, null));
			
			 // step 5
	        document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Inserimento nel file history

		String outputFile = BARCODEFOLDERHISTORY;
		
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();
			
		try {
			// use FileWriter constructor that specifies open for appending
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ';');
			
			// if the file didn't already exist then we need to write out the header line
			if (!alreadyExists)
			{
				csvOutput.write("Action");
				csvOutput.write("Company");
				csvOutput.write("Magazzino");
				csvOutput.write("Zona");
				csvOutput.write("Fornitore");
				csvOutput.write("Date");
				csvOutput.endRecord();
			}
		
			
			// write out a record
			csvOutput.write("INSERT");
			csvOutput.write(locazione.getC1orCH());
	
			csvOutput.write(locazione.getMagazzino());
			csvOutput.write(locazione.getZona());
			csvOutput.write(fornitore);
			csvOutput.write(new Date().toString());
		
			csvOutput.endRecord();

			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "locazioniresult";
		
	}
	
	@RequestMapping(value="/locazioni/delete", method = RequestMethod.POST)
	public String deleteLocazione(ModelMap model,HttpServletRequest request, @ModelAttribute("locazione")Locazione locazione) {
		
		//estraggo fornitore
		String fornitore =locazione.getCodFornitore();
		//fornitore=fornitore.concat("A2");
		int risultato = eliminaLocazioneTemporanea(fornitore, locazione.getC1orCH(), locazione.getMagazzino(), locazione.getZona());
		//String referer = request.getHeader("Referer");
		
		if(risultato > 0){
			model.addAttribute("risultato", "OK: Movimenti spostati e Locazioni Cancellate Correttamente");
		}
		else{
			model.addAttribute("risultato", "Errore: Qualcosa è andato storto o i movimenti sono già stati cancellati");
		}
		
		return "locazioniresult";
		
	}
	
	
	private int creaLocazioneTemporanea(String fornitore, String company, String magazzino, String zona){
		
		
		String url="jdbc:as400://iseries.ecsa.ch/EC700DFC1";
		 if(company.equals("C1")){
			  url = C1URL;
		 }else{
			 url = CHURL;
		 }
		 
		 Connection conn = null;
		 java.sql.Statement stmt = null;
		
		 int ok = 0;
		 try {		Class.forName(C1DRIVER);
			 	conn = DriverManager.getConnection(url, C1PWD, C1PWD );
			 	stmt = conn.createStatement();
			 	 
			 	String query = "SELECT * from  WHOLOC  where LOSROM ='"+magazzino.toUpperCase()+"' and LOLZON ='"+zona.toUpperCase()+"' and LOLCID ='"+fornitore+"'";
			 	
			 	ResultSet rs = stmt.executeQuery(query);
			 	 if(rs.next()){
			 		 return -100;
			 	 }
			 	//Creazione della locazione
				
				 query = "INSERT INTO WHOLOC (LOSROM, LOLZON, LOLCID, " 
								+"LOLOCS, LOMCLA, LOMITM, LOMPUT, "
								+"LOPIPR, LOPUPR, LOFRCA, LOCRTD, "
								+"LOOVRA, LOAURP) "
								+"VALUES(  '"+magazzino.toUpperCase()+"', '"+zona.toUpperCase()+"','"+ fornitore +"', 'BULK', "
								+"5, 'Y', 'Y', 200, 200, 9999999999.999, "
								+"'20140924', 'N', 'N')";

				 ok = stmt.executeUpdate(query);
				  if (ok !=0){
				 //Creazione della descrizione della locazione
				  query =  "INSERT INTO WHOLCD"
				 +"(LDSROM, LDLZON, LDLCID, LDLODE)"
				 +"VALUES ('"+magazzino.toUpperCase()+"','"+zona.toUpperCase()+"' ,'" +fornitore+"', 'COD"+fornitore+"')";
				  ok = stmt.executeUpdate(query);
				  }
				  if (ok !=0){
					  query = "update WHOLOP set LPLCID ='"+fornitore+"'"                       
						      +"WHERE LPPRDC in (SELECT pgprdc FROM sroprg "                   
							  +"WHERE pgmsup='"+fornitore+"')  and LPSROM ='"+magazzino.toUpperCase()+"' and LPLZON ='"+zona.toUpperCase()+"' and LPLCID ='"+zona.toUpperCase()+"'";
					  ok = stmt.executeUpdate(query);
				  }
				  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} 
		
	     return ok;
	}
	
private int eliminaLocazioneTemporanea(String fornitore, String company, String magazzino, String zona){
		
		
	
		String url="jdbc:as400://iseries.ecsa.ch/EC700DFC1";
		 if(company.equals("C1")){
			  url = C1URL;
			 
		 }else{
			 url = CHURL;
		 }
	 
		 Connection conn = null;
		 java.sql.Statement stmt = null;

		 
		 int ok = 0;
		 try {
			 	//Rispostamento dei saldi esistenti nella vecchia locazione
				Class.forName(C1DRIVER);
				 String query = "update WHOLOP set LPLCID ='"+zona.toUpperCase()+"'"                        
						 		+" WHERE LPSROM ='"+magazzino.toUpperCase()+"' and LPLZON ='"+zona.toUpperCase()+"' and LPLCID ='"+ fornitore+"'";

				 conn = DriverManager.getConnection(url, C1PWD, C1PWD );
				 stmt = conn.createStatement();
				 ok = stmt.executeUpdate(query);
				 
				 //Modifica dei movimenti di locazione
				 query = "update WHOLTR set LTLCID ='"+zona.toUpperCase()+"' "
						 +"where LTSROM ='"+magazzino.toUpperCase()+"' and LTLZON ='"+zona.toUpperCase()+"' and LTLCID ='"+fornitore+"'";
				 ok = stmt.executeUpdate(query);
				  
				 //Cancellazione della locazione
				query = "delete from  WHOLOC  where LOSROM ='"+magazzino.toUpperCase()+"' and LOLZON ='"+zona.toUpperCase()+"' and LOLCID ='"+fornitore+"'";
				ok = stmt.executeUpdate(query);
				  
				//Cancellazione della descrizione della locazione
				query = "Delete from  WHOLCD where LDSROM ='"+magazzino.toUpperCase()+"' and LDLZON ='"+zona.toUpperCase()+"' and LDLCID ='"+fornitore+"'";
				ok = stmt.executeUpdate(query);  
				 
				  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} 
		
		 
		 String outputFile = BARCODEFOLDERHISTORY;
			
			// before we open the file check to see if it already exists
			boolean alreadyExists = new File(outputFile).exists();
				
			try {
				// use FileWriter constructor that specifies open for appending
				CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ';');
				
				// if the file didn't already exist then we need to write out the header line
				if (!alreadyExists)
				{
					csvOutput.write("Action");
					csvOutput.write("Company");
					csvOutput.write("Magazzino");
					csvOutput.write("Zona");
					csvOutput.write("Fornitore");
					csvOutput.write("Date");
					csvOutput.endRecord();
				}
			
				
				// write out a record
				csvOutput.write("DELETE");
				csvOutput.write(company);
				csvOutput.write(magazzino.toUpperCase());
				csvOutput.write(zona.toUpperCase());
				csvOutput.write(fornitore);
				csvOutput.write(new Date().toString());
			
				csvOutput.endRecord();

				csvOutput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 
		 
		 
	     return ok;
	}
	
}
