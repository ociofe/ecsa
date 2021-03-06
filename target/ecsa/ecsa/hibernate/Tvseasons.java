// default package
// Generated 19-giu-2015 16.47.19 by Hibernate Tools 3.4.0.CR1
package main.webapp.ecsa.hibernate;
import java.util.Date;

/**
 * Tvseasons generated by hbm2java
 */
public class Tvseasons implements java.io.Serializable {

	private Integer id;
	private int seriesid;
	private int season;
	private Integer bannerrequest;
	private String locked;
	private Date mirrorupdate;
	private int lockedby;
	private boolean tmsWanted;

	public Tvseasons() {
	}

	public Tvseasons(int seriesid, int season, String locked,
			Date mirrorupdate, int lockedby, boolean tmsWanted) {
		this.seriesid = seriesid;
		this.season = season;
		this.locked = locked;
		this.mirrorupdate = mirrorupdate;
		this.lockedby = lockedby;
		this.tmsWanted = tmsWanted;
	}

	public Tvseasons(int seriesid, int season, Integer bannerrequest,
			String locked, Date mirrorupdate, int lockedby, boolean tmsWanted) {
		this.seriesid = seriesid;
		this.season = season;
		this.bannerrequest = bannerrequest;
		this.locked = locked;
		this.mirrorupdate = mirrorupdate;
		this.lockedby = lockedby;
		this.tmsWanted = tmsWanted;
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

	public int getSeason() {
		return this.season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public Integer getBannerrequest() {
		return this.bannerrequest;
	}

	public void setBannerrequest(Integer bannerrequest) {
		this.bannerrequest = bannerrequest;
	}

	public String getLocked() {
		return this.locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	public Date getMirrorupdate() {
		return this.mirrorupdate;
	}

	public void setMirrorupdate(Date mirrorupdate) {
		this.mirrorupdate = mirrorupdate;
	}

	public int getLockedby() {
		return this.lockedby;
	}

	public void setLockedby(int lockedby) {
		this.lockedby = lockedby;
	}

	public boolean isTmsWanted() {
		return this.tmsWanted;
	}

	public void setTmsWanted(boolean tmsWanted) {
		this.tmsWanted = tmsWanted;
	}

}
