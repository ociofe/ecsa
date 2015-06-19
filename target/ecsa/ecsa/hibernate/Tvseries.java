// default package
// Generated 19-giu-2015 16.47.19 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Tvseries generated by hbm2java
 */
public class Tvseries implements java.io.Serializable {

	private Integer id;
	private String seriesName;
	private String seriesId;
	private String status;
	private String firstAired;
	private String network;
	private Integer networkId;
	private String runtime;
	private String genre;
	private String actors;
	private String overview;
	private Integer bannerrequest;
	private Integer lastupdated;
	private String airsDayOfWeek;
	private String airsTime;
	private String rating;
	private Integer flagged;
	private Integer forceupdate;
	private Integer hits;
	private int updateId;
	private String requestcomment;
	private String locked;
	private Date mirrorupdate;
	private int lockedby;
	private String autoimport;
	private String disabled;
	private String imdbId;
	private String zap2itId;
	private Date added;
	private Integer addedBy;
	private boolean tmsWantedOld;
	private int tmsPriority;
	private Byte tmsWanted;

	public Tvseries() {
	}

	public Tvseries(String seriesName, int updateId, String requestcomment,
			String locked, Date mirrorupdate, int lockedby, String disabled,
			boolean tmsWantedOld, int tmsPriority) {
		this.seriesName = seriesName;
		this.updateId = updateId;
		this.requestcomment = requestcomment;
		this.locked = locked;
		this.mirrorupdate = mirrorupdate;
		this.lockedby = lockedby;
		this.disabled = disabled;
		this.tmsWantedOld = tmsWantedOld;
		this.tmsPriority = tmsPriority;
	}

	public Tvseries(String seriesName, String seriesId, String status,
			String firstAired, String network, Integer networkId,
			String runtime, String genre, String actors, String overview,
			Integer bannerrequest, Integer lastupdated, String airsDayOfWeek,
			String airsTime, String rating, Integer flagged,
			Integer forceupdate, Integer hits, int updateId,
			String requestcomment, String locked, Date mirrorupdate,
			int lockedby, String autoimport, String disabled, String imdbId,
			String zap2itId, Date added, Integer addedBy, boolean tmsWantedOld,
			int tmsPriority, Byte tmsWanted) {
		this.seriesName = seriesName;
		this.seriesId = seriesId;
		this.status = status;
		this.firstAired = firstAired;
		this.network = network;
		this.networkId = networkId;
		this.runtime = runtime;
		this.genre = genre;
		this.actors = actors;
		this.overview = overview;
		this.bannerrequest = bannerrequest;
		this.lastupdated = lastupdated;
		this.airsDayOfWeek = airsDayOfWeek;
		this.airsTime = airsTime;
		this.rating = rating;
		this.flagged = flagged;
		this.forceupdate = forceupdate;
		this.hits = hits;
		this.updateId = updateId;
		this.requestcomment = requestcomment;
		this.locked = locked;
		this.mirrorupdate = mirrorupdate;
		this.lockedby = lockedby;
		this.autoimport = autoimport;
		this.disabled = disabled;
		this.imdbId = imdbId;
		this.zap2itId = zap2itId;
		this.added = added;
		this.addedBy = addedBy;
		this.tmsWantedOld = tmsWantedOld;
		this.tmsPriority = tmsPriority;
		this.tmsWanted = tmsWanted;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSeriesName() {
		return this.seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getSeriesId() {
		return this.seriesId;
	}

	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFirstAired() {
		return this.firstAired;
	}

	public void setFirstAired(String firstAired) {
		this.firstAired = firstAired;
	}

	public String getNetwork() {
		return this.network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public Integer getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(Integer networkId) {
		this.networkId = networkId;
	}

	public String getRuntime() {
		return this.runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getActors() {
		return this.actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getOverview() {
		return this.overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public Integer getBannerrequest() {
		return this.bannerrequest;
	}

	public void setBannerrequest(Integer bannerrequest) {
		this.bannerrequest = bannerrequest;
	}

	public Integer getLastupdated() {
		return this.lastupdated;
	}

	public void setLastupdated(Integer lastupdated) {
		this.lastupdated = lastupdated;
	}

	public String getAirsDayOfWeek() {
		return this.airsDayOfWeek;
	}

	public void setAirsDayOfWeek(String airsDayOfWeek) {
		this.airsDayOfWeek = airsDayOfWeek;
	}

	public String getAirsTime() {
		return this.airsTime;
	}

	public void setAirsTime(String airsTime) {
		this.airsTime = airsTime;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Integer getFlagged() {
		return this.flagged;
	}

	public void setFlagged(Integer flagged) {
		this.flagged = flagged;
	}

	public Integer getForceupdate() {
		return this.forceupdate;
	}

	public void setForceupdate(Integer forceupdate) {
		this.forceupdate = forceupdate;
	}

	public Integer getHits() {
		return this.hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public int getUpdateId() {
		return this.updateId;
	}

	public void setUpdateId(int updateId) {
		this.updateId = updateId;
	}

	public String getRequestcomment() {
		return this.requestcomment;
	}

	public void setRequestcomment(String requestcomment) {
		this.requestcomment = requestcomment;
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

	public String getAutoimport() {
		return this.autoimport;
	}

	public void setAutoimport(String autoimport) {
		this.autoimport = autoimport;
	}

	public String getDisabled() {
		return this.disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getImdbId() {
		return this.imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getZap2itId() {
		return this.zap2itId;
	}

	public void setZap2itId(String zap2itId) {
		this.zap2itId = zap2itId;
	}

	public Date getAdded() {
		return this.added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	public Integer getAddedBy() {
		return this.addedBy;
	}

	public void setAddedBy(Integer addedBy) {
		this.addedBy = addedBy;
	}

	public boolean isTmsWantedOld() {
		return this.tmsWantedOld;
	}

	public void setTmsWantedOld(boolean tmsWantedOld) {
		this.tmsWantedOld = tmsWantedOld;
	}

	public int getTmsPriority() {
		return this.tmsPriority;
	}

	public void setTmsPriority(int tmsPriority) {
		this.tmsPriority = tmsPriority;
	}

	public Byte getTmsWanted() {
		return this.tmsWanted;
	}

	public void setTmsWanted(Byte tmsWanted) {
		this.tmsWanted = tmsWanted;
	}

}
