package main.webapp.show.util;

import java.util.Comparator;

public class SeriePesata implements Comparator<SeriePesata> {

	public int seriesid;
	public int languageid;
	public String translation;
	public double peso;
	
	public int getSeriesid() {
		return seriesid;
	}
	public void setSeriesid(int seriesid) {
		this.seriesid = seriesid;
	}
	public int getLanguageid() {
		return languageid;
	}
	public void setLanguageid(int languageid) {
		this.languageid = languageid;
	}
	public String getTranslation() {
		return translation;
	}
	public void setTranslation(String translation) {
		this.translation = translation;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public int compare(SeriePesata o1, SeriePesata o2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
