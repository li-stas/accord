package accord.mvc.model;

import java.util.List;

public class DBAccordOrdersJSONImpl  {
    private int status;
    private int lastRec;
    private List<?> orderList;

    public DBAccordOrdersJSONImpl() {
    }

    public DBAccordOrdersJSONImpl(int status, int lastRec) {
        this.status = status;
        this.lastRec = lastRec;
    }

    public DBAccordOrdersJSONImpl(int status) {
        this.status = status;
    }

    public List<?> getOrderList() {
        return orderList;
    }

    public void setOrderList(Object orderList) {
        this.orderList = (List<?>) orderList;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLastRec() {
        return lastRec;
    }

    public void setLastRec(int lastRec) {
        this.lastRec = lastRec;
    }
}
