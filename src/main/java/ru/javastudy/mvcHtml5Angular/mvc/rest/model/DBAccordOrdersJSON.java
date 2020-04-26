package ru.javastudy.mvcHtml5Angular.mvc.rest.model;

import java.util.List;

public class DBAccordOrdersJSON {
    //private List<DBAccordOrderRs1AndRs2JSON> orderList;
    private List<Object> orderList;

    //public List<DBAccordOrderRs1AndRs2JSON> getOrderList() {
    public List<Object> getOrderList() {
        return orderList;
    }

    //public void setOrderList(List<DBAccordOrderRs1AndRs2JSON> orderList) {
    public void setOrderList(Object orderList) {
        this.orderList = (List<Object>) orderList;
    }
}
