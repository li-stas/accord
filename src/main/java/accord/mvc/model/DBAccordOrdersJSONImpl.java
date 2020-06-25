package accord.mvc.model;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBAccordOrdersJSONImpl that = (DBAccordOrdersJSONImpl) o;
        return status == that.status &&
                lastRec == that.lastRec;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, lastRec);
    }
}
