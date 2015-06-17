package main.webapp.ociofe.model;

public class SeriesUpdate {

	// Default serial UID
    private static final long serialVersionUID = 1L;
    private String id;
    private String time;

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

//    @Override
//    public String toString() {
//        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
//    }
}


