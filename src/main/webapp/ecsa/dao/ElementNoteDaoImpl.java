package main.webapp.ecsa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ElementNoteDaoImpl implements ElementNoteDao {

	public ArrayList<ElementNote> Populate (ResultSet rs) {
		ArrayList<ElementNote> data = new ArrayList<ElementNote>();
	     
		try {
			while (rs.next()) {
				 ElementNote e = new ElementNote();
				 
				 e.setGenerator(Integer.valueOf(rs.getString("MHPGNO")));
				 
				 e.setProgramDescription(rs.getString("MHPGDE").trim());
				 e.setModificationNumber(rs.getString("MHMONR").trim());
				 e.setDescription(rs.getString("MHDESC").trim());
				 e.setTextDescription(rs.getString("MHT256").trim());
				 e.setRelease(rs.getString("MHRELL").trim());
				 e.setFileDetails(rs.getString("MHFILD").trim());
				 e.setProgramDetails(rs.getString("MHPGMD").trim());
				 e.setMessageDetails(rs.getString("MHMSGD").trim());
				 e.setFieldDetails(rs.getString("MHFLDD").trim());
				 e.setMenuOptionDetails(rs.getString("MHMOPD").trim());
				 e.setWindowDetails(rs.getString("MHWNDD").trim());
				 e.setStatus(rs.getString("MHSTAT").trim());
				 e.setInternalName(rs.getString("MHPSNA").trim());
				 e.setAnalyserName(rs.getString("MHANAL").trim());
				 e.setTesterName(rs.getString("MHTEST").trim());
				 e.setTestedOnDate(Integer.valueOf(rs.getString("MHTDAT").trim()));
				 e.setPromotedToLiveOnDate(Integer.valueOf(rs.getString("MHPDAT").trim()));
				 e.setTextKey(Integer.valueOf(rs.getString("MHTXKY").trim()));
				 e.setAttachmentName(rs.getString("MHATNA").trim());
				 
				 
				 data.add(e);
				 
			 }
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
