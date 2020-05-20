package accord.mvc.rest.model;

public class DBAccordOrderRs2 {
    // строки товар
    private int mnTov;
    private float kvp;
    private float zen;

    public DBAccordOrderRs2() {
    }

    public int getMnTov() {
        return mnTov;
    }

    public void setMnTov(int mnTov) {
        this.mnTov = mnTov;
    }

    public float getKvp() {
        return kvp;
    }

    public void setKvp(float kvp) {
        this.kvp = kvp;
    }

    public float getZen() {
        return zen;
    }

    public void setZen(float zen) {
        this.zen = zen;
    }

    @Override
    public String toString() {
        return "Rs2{" +
                "mnTov=" + mnTov +
                ", kvp=" + kvp +
                ", zen=" + zen +
                '}';
    }
}
