// default package
// Generated 19-giu-2015 16.47.19 by Hibernate Tools 3.4.0.CR1
package main.webapp.ecsa.hibernate;
import java.util.Date;

/**
 * TranslationEpisodeoverview generated by hbm2java
 */
public class TranslationEpisodeoverview implements java.io.Serializable {

	private Integer id;
	private int episodeid;
	private int languageid;
	private String translation;
	private Date mirrorupdate;

	public TranslationEpisodeoverview() {
	}

	public TranslationEpisodeoverview(int episodeid, int languageid,
			Date mirrorupdate) {
		this.episodeid = episodeid;
		this.languageid = languageid;
		this.mirrorupdate = mirrorupdate;
	}

	public TranslationEpisodeoverview(int episodeid, int languageid,
			String translation, Date mirrorupdate) {
		this.episodeid = episodeid;
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

	public int getEpisodeid() {
		return this.episodeid;
	}

	public void setEpisodeid(int episodeid) {
		this.episodeid = episodeid;
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
