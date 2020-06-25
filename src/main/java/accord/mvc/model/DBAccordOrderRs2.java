package accord.mvc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class DBAccordOrderRs2 implements Serializable {
    //private static final long serialVersionUID = 12345678L;
    // строки товар
    private int ttn;
    private int mnTov;
    private String nat;
    private double kvp;
    private double zen;
    private double svp;

    public DBAccordOrderRs2() {
    }

    public DBAccordOrderRs2(int ttn, int mnTov, String nat, double kvp, double zen) {
        this.ttn = ttn;
        this.mnTov = mnTov;
        this.nat = nat;
        this.kvp = kvp;
        this.zen = zen;
    }

    public int getTtn() {
        return ttn;
    }

    public void setTtn(int ttn) {
        this.ttn = ttn;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public int getMnTov() {
        return mnTov;
    }

    public void setMnTov(int mnTov) {
        this.mnTov = mnTov;
    }

    public double getKvp() {
        return kvp;
    }

    public void setKvp(double kvp) {
        this.kvp = kvp;
        this.svp = BigDecimal.valueOf(this.zen * this.kvp).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double getZen() {
        return zen;
    }

    public void setZen(double zen) {
        this.zen = BigDecimal.valueOf(zen).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.svp = BigDecimal.valueOf(this.zen * this.kvp).setScale(2, RoundingMode.HALF_UP).doubleValue();

    }

    public double getSvp() {
        return svp;
    }

    @Override
    public String toString() {
        return "Rs2{" +
                "mnTov=" + mnTov +
                ", kvp=" + kvp +
                ", zen=" + zen +
                ", svp=" + svp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBAccordOrderRs2 that = (DBAccordOrderRs2) o;
        return ttn == that.ttn &&
                mnTov == that.mnTov;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ttn, mnTov);
    }
}
