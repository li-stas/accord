package accord.mvc.model;

import java.util.Objects;

public class DBAccordOrderTov {
    private int mnTov; //NUMERIC(7) NOT NuLL,
    private String nat; // VARCHAR(90) NOT NuLL,
    private double osFo; // FLOAT NOT NuLL,
    private String nei; // VARCHAR(12) NOT NuLL,
    private double cenPr; // FLOAT NOT NuLL,


    public DBAccordOrderTov() {
    }

    public int getMnTov() {
        return mnTov;
    }

    public void setMnTov(int mnTov) {
        this.mnTov = mnTov;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public double getOsFo() {
        return osFo;
    }

    public void setOsFo(double osFo) {
        this.osFo = osFo;
    }

    public String getNei() {
        return nei;
    }

    public void setNei(String nei) {
        this.nei = nei;
    }

    public double getCenPr() {
        return cenPr;
    }

    public void setCenPr(double cenPr) {
        this.cenPr = cenPr;
    }

    @Override
    public String toString() {
        return "DBAccordOrderTov{" +
                "mnTov=" + mnTov +
                ", nat='" + nat + '\'' +
                ", osFo=" + osFo +
                ", nei='" + nei + '\'' +
                ", cenPr=" + cenPr +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBAccordOrderTov that = (DBAccordOrderTov) o;
        return mnTov == that.mnTov;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mnTov);
    }
}
