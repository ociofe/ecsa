package main.webapp.show.controller;
 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.webapp.ecsa.dao.ElementNote;
import main.webapp.ecsa.dao.ElementNoteDao;
import main.webapp.ecsa.dao.FileModel;
import main.webapp.ecsa.dao.Locazione;
import main.webapp.ecsa.dao.Login;
import main.webapp.show.util.FileUploadForm;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
 
@Component
@Controller
@RequestMapping("/")
public class BaseController {
	
	@Value("${attachment.folder.note}")
    private String attachemntFolder;
	
	private ElementNoteDao ElementNote;
	
		
	 static Logger LOG = Logger.getLogger(
			 BaseController.class.toString());
	
	 @RequestMapping(value="/", method = RequestMethod.GET)
		public String login(ModelMap model) {
		 
		 Login login = new Login();
		 model.addAttribute("login", login);
		 
		 return "login";
		 
	 }
	 
	 @RequestMapping(value="/login", method = RequestMethod.POST)
		public String loginPost(ModelMap model,HttpServletRequest request, @ModelAttribute("locazione")Login login) {
		 
		 if(login.getPassword().equals("nimda") && login.getUsername().equals("admin") ){
			 return "index";
		 }
		 model.addAttribute("errorMessage", "Credenziali Errate");
//		 String referer = request.getHeader("Referer");
		return "login";
		 
	 }
	 
	 @RequestMapping(value="/index", method = RequestMethod.GET)
		public String index(ModelMap model) {
		 return "index";
		 
	 }
	 
	@RequestMapping(value="/notemanagement", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
		
		try {
			 
//			 props = new Properties();
//			 props.load(new FileInputStream("properties/mydb2.properties"));
			 
			 String DRIVER = "com.ibm.as400.access.AS400JDBCDriver"; 
			 String URL = "jdbc:as400://iseries.ecsa.ch/EC700DFC1";
			 Connection conn = null;
			 java.sql.Statement stmt = null;
			 //TODO Selezionare ultimo inserito e passarlo alla form
			 
			 //Connect to iSeries 
			 //AS400JDBCDriver
			 
			 //Estraggo tutti i risultati e li passo alla pagina 
			 Class.forName(DRIVER); 
			 conn = DriverManager.getConnection(URL, "FEDDEL", "FEDDEL" );
			 String query = "SELECT * FROM COMMON.Z2OMDBH ORDER BY MHPGNO";
			 stmt = conn.createStatement();
		     ResultSet rs = stmt.executeQuery(query);
		     ArrayList<ElementNote> data = ElementNote.Populate(rs);
		     model.addAttribute("data", data);
		     model.addAttribute("elementNote", new ElementNote());
		     
		     //Estraggo l'ultimo id generato sommo uno e lo passo alla pagina.
		     String queryLast = "SELECT MHPGNO FROM COMMON.Z2OMDBH ORDER BY MHPGNO DESC";
		     rs = stmt.executeQuery(queryLast);
		     if(rs.next()){
		    	 model.addAttribute("lastNote", Integer.valueOf(rs.getString("MHPGNO"))+1);
		     }
			 conn.close();
			 
			}
			catch (Exception e) {
				//LOG.info(e.toString());
			 System.out.println(e);
			}
 
		
 
		//Spring uses InternalResourceViewResolver and return back index.jsp
		return "insertnote";
 
	}
	
	   @RequestMapping(value = "/resultPage", method = RequestMethod.POST)
	   public String addNote(HttpServletRequest request, @ModelAttribute("elementNote")ElementNote note, 
	   ModelMap model) {
		   
		   
		   String DRIVER = "com.ibm.as400.access.AS400JDBCDriver"; 
		   String URL = "jdbc:as400://iseries.ecsa.ch/EC700DFC1";
		   Connection conn = null;
		   java.sql.Statement stmt = null;

		   //create SQL statment
		   String query = "INSERT INTO COMMON.Z2OMDBH (MHPGNO,MHPGDE,MHMONR,MHDESC,MHT256,MHRELL,MHFILD,MHPGMD,MHMSGD,MHFLDD,MHMOPD,MHWNDD,MHSTAT,MHPSNA,MHANAL,MHTEST,MHTDAT,MHPDAT,MHTXKY,MHATNA ) VALUES ";
		   query = query.concat("("+format(Integer.toString(note.getGenerator()))+ format(note.getProgramDescription()) + format(note.getModificationNumber()) + format(note.getDescription()) + format(note.getTextDescription()) + format(note.getRelease()) + format(note.getFileDetails()) + format(note.getProgramDetails()) + format(note.getMessageDetails()) + format(note.getFieldDetails()) + format(note.getMenuOptionDetails()) + format(note.getWindowDetails()) + format(note.getStatus()) + format(note.getInternalName()) + format(note.getAnalyserName()) + format(note.getTesterName()) + format(Integer.toString(note.getTestedOnDate())) + format(Integer.toString(note.getPromotedToLiveOnDate())) + format(Integer.toString(note.getTextKey())) + "'"+note.getAttachmentName()+"')");
		   String result = executeUpdate(query);
		   model.addAttribute("sqlErrorMessage", result);  
		  String referer = request.getHeader("Referer");
	      
	      return "redirect:"+ referer;
	 
	      
	      
	      //return "resultPage";
	   }
	   
	   @RequestMapping(value = "/noteModify/test", method = RequestMethod.POST)
	   public String modifyNote(HttpServletRequest request, @ModelAttribute("elementNote")ElementNote note, 
	   ModelMap model) {
		   
		   
		   String DRIVER = "com.ibm.as400.access.AS400JDBCDriver"; 
		   String URL = "jdbc:as400://iseries.ecsa.ch/EC700DFC1";
		   Connection conn = null;
		   java.sql.Statement stmt = null;

		   //create SQL statment
		   //String query = "UPDATE COMMON.Z2OMDBH SET MHPGNO,MHPGDE,MHMONR,MHDESC,MHT256,MHRELL,MHFILD,MHPGMD,MHMSGD,MHFLDD,MHMOPD,MHWNDD,MHSTAT,MHPSNA,MHANAL,MHTEST,MHTDAT,MHPDAT,MHTXKY,MHATNA ) VALUES ";
//		  System.out.println(format(note.getFileDetails()));
//		  System.out.println(note.getFileDetails());
		  // String query = ",MHMSGD,MHFLDD,MHMOPD,MHWNDD,MHSTAT,MHPSNA,,,,,, ) VALUES ";
		   //CreateQuery
		   String query = "UPDATE COMMON.Z2OMDBH SET ";
		   query = createUpdateQuery(query,"MHPGDE",note.getProgramDescription(),false);
		   query = createUpdateQuery(query,"MHMONR",note.getModificationNumber(),false);
		   
		   query = createUpdateQuery(query,"MHDESC",note.getDescription(),false);
		   
		   query = createUpdateQuery(query,"MHT256",note.getTextDescription(),false);
		   
		   query = createUpdateQuery(query,"MHRELL",note.getRelease(),false);
		   
		   query = createUpdateQuery(query,"MHFILD",note.getFileDetails(),false);
		   
		   query = createUpdateQuery(query,"MHPGMD",note.getProgramDetails(),false);
		   
		   query = createUpdateQuery(query,"MHMSGD",note.getMessageDetails(),false);
		   
		   query = createUpdateQuery(query,"MHFLDD",note.getFieldDetails(),false);
		   
		   query = createUpdateQuery(query,"MHMOPD",note.getMenuOptionDetails(),false);
		   
		   query = createUpdateQuery(query,"MHWNDD",note.getWindowDetails(),false);
		   query = createUpdateQuery(query,"MHSTAT",note.getStatus(),false);
		   query = createUpdateQuery(query,"MHPSNA",note.getInternalName(),false);
		   query = createUpdateQuery(query,"MHANAL",note.getAnalyserName(),false);
		   query = createUpdateQuery(query,"MHTEST",note.getTesterName(),false);
		   query = createUpdateQuery(query,"MHTDAT",Integer.toString(note.getTestedOnDate()),false);
		   query = createUpdateQuery(query,"MHPDAT",Integer.toString(note.getPromotedToLiveOnDate()),false);
		   query = createUpdateQuery(query,"MHTXKY",Integer.toString(note.getTextKey()),false);
		   query = createUpdateQuery(query,"MHATNA",note.getAttachmentName(),true);
		  
		   
		   query=query.concat("WHERE MHPGNO ='"+note.getGenerator()+"'");
		  
		 // query = query.concat("("+format(Integer.toString(note.getGenerator()))+ format(note.getProgramDescription()) + format(note.getModificationNumber()) + format(note.getDescription()) + format(note.getTextDescription()) + format(note.getRelease()) + format(note.getFileDetails()) + format(note.getProgramDetails()) + format(note.getMessageDetails()) + format(note.getFieldDetails()) + format(note.getMenuOptionDetails()) + format(note.getWindowDetails()) + format(note.getStatus()) + format(note.getInternalName()) + format(note.getAnalyserName()) + format(note.getTesterName()) + format(Integer.toString(note.getTestedOnDate())) + format(Integer.toString(note.getPromotedToLiveOnDate())) + format(Integer.toString(note.getTextKey())) + "'"+note.getAttachmentName()+"')");
		   System.out.println(query);
		   String result = executeUpdate(query);
		   model.addAttribute("sqlErrorMessage", result);  
		  String referer = request.getHeader("Referer");
	      
	      return "redirect:"+ referer;
	 
	      
	      
	      //return "resultPage";
	   }
	   
	   //RIMUOVE NOTA
	   @RequestMapping(value = "remove", method = RequestMethod.GET)
	   public String removeAd(@RequestParam("idToDelete") String idToDelete ,ModelMap model, HttpServletRequest request) {
	        
		   System.out.println(idToDelete);
		   String referer = request.getHeader("Referer");
		   String query = "DELETE FROM COMMON.Z2OMDBH WHERE MHPGNO='"+idToDelete+"'";
		   String result = executeUpdate(query);
		   model.addAttribute("sqlErrorMessage", result);
		   return "redirect:"+ referer;
	   }
	   
	   
	   //POPOLA FORM PER MODIFICA NOTA
	   @RequestMapping(value = "noteModify", method = RequestMethod.GET)
	   public String modifyAd(@RequestParam("idToModify") String idToModify ,ModelMap model, HttpServletRequest request) {
	        
		   System.out.println(idToModify);
		   ElementNote elementNote = executeSelectNote(idToModify);
		   model.addAttribute("note", elementNote);
		   model.addAttribute("elementNote", new ElementNote());
		   
		   model.addAttribute("fileToUpload", new String());
		   
		   String sourceDirectory = attachemntFolder+idToModify;
		   
		   File f = new File(sourceDirectory);
		   //estraggo i file dala cartella
	       File[] fileObjects = f.listFiles();
	       //System.out.println(fileObjects[1].getName());
	      
	       ArrayList<FileModel> files = new ArrayList<FileModel>();
	      if(fileObjects != null){
	       for(int i=0;i<fileObjects.length;i++){
	    	   files.add(new FileModel(fileObjects[i].getName(), fileObjects[i].getAbsolutePath()));
	       }
	      }
	       model.addAttribute("fileList", files);
		   return "noteModify";
	   }
	   
	   
	    @RequestMapping(value = "noteModify/upload", method = RequestMethod.POST)
	    public String save(
	            @ModelAttribute("uploadForm") FileUploadForm uploadForm,
	                    Model map, HttpServletRequest request,@RequestParam("idToUpload") String idToUpload) {
	         
	        List<MultipartFile> files = uploadForm.getFiles();
	 
	        List<String> fileNames = new ArrayList<String>();
	         
	        if(null != files && files.size() > 0) {
	            for (MultipartFile multipartFile : files) {
	            	
	                String fileName = multipartFile.getOriginalFilename();
	                
	                if (!"".equalsIgnoreCase(fileName)) {
	                    // Handle file content - multipartFile.getInputStream()
	                    try {
	                    	
	                    	//String result =request.getSession().getServletContext().getRealPath("/resources/attachment");
	                    	new File(attachemntFolder+idToUpload).mkdirs();
							multipartFile
							        .transferTo(new File(attachemntFolder+idToUpload+"/" + fileName));
							fileNames.add(fileName);
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                    fileNames.add(fileName);
	                }
	                
	                
	                //Handle file content - multipartFile.getInputStream()
	 
	            }
	        }
	        String referer = request.getHeader("Referer");  
		    return "redirect:"+ referer;
	    }
 
	    
	    @RequestMapping(value = "noteModify/download", method = RequestMethod.POST)
	    public void download(Model map, @RequestParam("idToModify") String idToModify,HttpServletResponse response , HttpServletRequest request,@ModelAttribute("fileToUpload")String fileToUpload) {
	    	
	    	response.setContentType("application/octet-stream");
	    	response.setHeader("Content-Disposition",
	    			"attachment;filename="+fileToUpload);
	    	System.out.println(fileToUpload);
	    	System.out.println(idToModify);
	    	
	    
	    	
	    	String pathFile= attachemntFolder+idToModify+"/" + fileToUpload;
	    	File tempFile = new File(pathFile);
	    	
	    	
	    	try {
	
	            InputStream in = new BufferedInputStream(new FileInputStream(tempFile));
	            
	            //Controllo dimensione File
	            //int ava= in.available();
	            
	            ServletOutputStream out = response.getOutputStream();
	            IOUtils.copy(in, out);
	            response.flushBuffer();
	            response.getOutputStream().flush();
	            response.getOutputStream().close(); 
	            in.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    }

	   
	//esegue query di insert o modifica a db   
	private String executeUpdate(String query){
		
		String DRIVER = "com.ibm.as400.access.AS400JDBCDriver"; 
	   String URL = "jdbc:as400://iseries.ecsa.ch/EC700DFC1";
	   Connection conn = null;
	   java.sql.Statement stmt = null;

		 try {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(URL, "FEDDEL", "FEDDEL" );
			stmt = conn.createStatement();
			
			 String result = String.valueOf(stmt.executeUpdate(query));
			conn.close();
			return result;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		 
	
	
	}
	private ElementNote executeSelectNote(String id){
		
	   String DRIVER = "com.ibm.as400.access.AS400JDBCDriver"; 
	   String URL = "jdbc:as400://iseries.ecsa.ch/EC700DFC1";
	   Connection conn = null;
	   java.sql.Statement stmt = null;

		 try {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(URL, "FEDDEL", "FEDDEL" );
			stmt = conn.createStatement();
			String query = "SELECT * FROM COMMON.Z2OMDBH WHERE MHPGNO='"+id+"'";
			ResultSet result = stmt.executeQuery(query);
			
		    
			ArrayList<ElementNote> data = ElementNote.Populate(result);
			conn.close();
			if(!data.isEmpty()){
				return  data.get(0);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
			return null; 	
	}
	
	private String format(String s){
		return "'"+s+"', ";
	}
	   
	private String createUpdateQuery( String query, String dbField, String value, boolean last){
		if(value != null){
			if (!last){
				return  query + dbField+"='"+value.trim()+"',";
			}else{
				return  query + dbField+"='"+value.trim()+"' ";
			
			}
		}
		else{
			return  query;
			}
			
		
	}

	public ElementNoteDao getElementNote() {
		return ElementNote;
	}

	public void setElementNote(ElementNoteDao elementNote) {
		ElementNote = elementNote;
	}
 
}