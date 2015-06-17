package main.webapp.ecsa.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface ElementNoteDao {

	public ArrayList<ElementNote> Populate(ResultSet rs);
	
}
