package main.webapp.show.controller;

import java.util.List;

public class OrderStatusData {
    private String status;
    private String orderNumber;
    private List<OrderLineData> orderLines;

    public String getStatus() {
          return status;
    }

    public void setStatus(String status) {
          this.status = status;
    }

    /**
    * @return the orderNumber
    */
    public String getOrderNumber() {
          return orderNumber;
    }

    /**
    * @param orderNumber
    *            the orderNumber to set
    */
    public void setOrderNumber(String orderNumber) {
          this.orderNumber = orderNumber;
    }

    /**
    * @return the orderLines
    */
    public List<OrderLineData> getOrderLines() {
          return orderLines;
    }

    /**
    * @param orderLines
    *            the orderLines to set
    */
    public void setOrderLines(List<OrderLineData> orderLines) {
          this.orderLines = orderLines;
    }

}


