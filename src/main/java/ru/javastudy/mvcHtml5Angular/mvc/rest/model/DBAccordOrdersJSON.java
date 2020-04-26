package ru.javastudy.mvcHtml5Angular.mvc.rest.model;

import java.util.List;

public class DBAccordOrdersJSON {
    private List<DBAccordOrderRs1AndRs2JSON> orderList;

    public List<DBAccordOrderRs1AndRs2JSON> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<DBAccordOrderRs1AndRs2JSON> orderList) {
        this.orderList = orderList;
    }
}
