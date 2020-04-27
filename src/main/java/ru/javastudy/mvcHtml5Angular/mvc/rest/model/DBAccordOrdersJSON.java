package ru.javastudy.mvcHtml5Angular.mvc.rest.model;

import java.util.List;

public class DBAccordOrdersJSON {
    private int status;
    //private List<DBAccordOrderRs1AndRs2JSON> orderList;
    private List<Object> orderList;

    public DBAccordOrdersJSON() {
    }

    public DBAccordOrdersJSON(int status) {
        this.status = status;
    }

    //public List<DBAccordOrderRs1AndRs2JSON> getOrderList() {
    public List<Object> getOrderList() {
        return orderList;
    }

    //public void setOrderList(List<DBAccordOrderRs1AndRs2JSON> orderList) {
    public void setOrderList(Object orderList) {
        this.orderList = (List<Object>) orderList;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
