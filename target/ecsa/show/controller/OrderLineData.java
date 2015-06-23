package main.webapp.show.controller;

public class OrderLineData {
	private String lineStatus;
    private String trackigCode;
    private String trackigUrl;
    private Long quantityRefund;

    /**
    * @return the lineStatus
    */
    public String getLineStatus() {
          return lineStatus;
    }

    /**
    * @param lineStatus
    *            the lineStatus to set
    */
    public void setLineStatus(String lineStatus) {
          this.lineStatus = lineStatus;
    }

    /**
    * @return the trackigCode
    */
    public String getTrackigCode() {
          return trackigCode;
    }

    /**
    * @param trackigCode
    *            the trackigCode to set
    */
    public void setTrackigCode(String trackigCode) {
          this.trackigCode = trackigCode;
    }

    /**
    * @return the trackigUrl
    */
    public String getTrackigUrl() {
          return trackigUrl;
    }

    /**
    * @param trackigUrl
    *            the trackigUrl to set
    */
    public void setTrackigUrl(String trackigUrl) {
          this.trackigUrl = trackigUrl;
    }

    /**
    * @return the quantityRefund
    */
    public Long getQuantityRefund() {
          return quantityRefund;
    }

    /**
    * @param quantityRefund
    *            the quantityRefund to set
    */
    public void setQuantityRefund(Long quantityRefund) {
          this.quantityRefund = quantityRefund;
    }


}
