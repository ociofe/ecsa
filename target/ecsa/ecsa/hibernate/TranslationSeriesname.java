// default package
// Generated 19-giu-2015 16.47.19 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * TranslationSeriesname generated by hbm2java
 */
public class TranslationSeriesname implements java.io.Serializable {

	private Integer id;
	private int seriesid;
	private int languageid;
	private String translation;
	private Date mirrorupdate;

	public TranslationSeriesname() {
	}

	public TranslationSeriesname(int seriesid, int languageid,
			String translation, Date mirrorupdate) {
		this.seriesid = seriesid;
		this.languageid = languageid;
		this.translation = translation;
		this.mirrorupdate = mirrorupdate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSeriesid() {
		return this.seriesid;
	}

	public void setSeriesid(int seriesid) {
		this.seriesid = seriesid;
	}

	public int getLanguageid() {
		return this.languageid;
	}

	public void setLanguageid(int languageid) {
		this.languageid = languageid;
	}

	public String getTranslation() {
		return this.translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public Date getMirrorupdate() {
		return this.mirrorupdate;
	}

	public void setMirrorupdate(Date mirrorupdate) {
		this.mirrorupdate = mirrorupdate;
	}

}
