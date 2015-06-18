// default package
// Generated 13-feb-2015 13.52.38 by Hibernate Tools 3.4.0.CR1
package main.webapp.ecsa.hibernate;
/**
 * Networks generated by hbm2java
 */
public class Networks implements java.io.Serializable {

	private Integer netId;
	private String network;
	private String comment;
	private String wikipedia;
	private String logo;
	private String iso6393;
	private String iso31661;

	public Networks() {
	}

	public Networks(String network) {
		this.network = network;
	}

	public Networks(String network, String comment, String wikipedia,
			String logo, String iso6393, String iso31661) {
		this.network = network;
		this.comment = comment;
		this.wikipedia = wikipedia;
		this.logo = logo;
		this.iso6393 = iso6393;
		this.iso31661 = iso31661;
	}

	public Integer getNetId() {
		return this.netId;
	}

	public void setNetId(Integer netId) {
		this.netId = netId;
	}

	public String getNetwork() {
		return this.network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getWikipedia() {
		return this.wikipedia;
	}

	public void setWikipedia(String wikipedia) {
		this.wikipedia = wikipedia;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getIso6393() {
		return this.iso6393;
	}

	public void setIso6393(String iso6393) {
		this.iso6393 = iso6393;
	}

	public String getIso31661() {
		return this.iso31661;
	}

	public void setIso31661(String iso31661) {
		this.iso31661 = iso31661;
	}

}