package accord.mvc.model;

import java.util.Objects;

public class DBAccordOrderTMesto {
    private int tMesto; //NUMERIC(7) NOT NuLL,
    private int kPl; //NUMERIC(7) NOT NuLL,
    private int kGp; //NUMERIC(7) NOT NuLL,
    private String ntMesto; //VARCHAR(190) NOT NuLL,

    public DBAccordOrderTMesto() {
    }

    public int gettMesto() {
        return tMesto;
    }

    public void settMesto(int tMesto) {
        this.tMesto = tMesto;
    }

    public int getkPl() {
        return kPl;
    }

    public void setkPl(int kPl) {
        this.kPl = kPl;
    }

    public int getkGp() {
        return kGp;
    }

    public void setkGp(int kGp) {
        this.kGp = kGp;
    }

    public String getNtMesto() {
        return ntMesto;
    }

    public void setNtMesto(String ntMesto) {
        this.ntMesto = ntMesto;
    }

    @Override
    public String toString() {
        return "DBAccordOrderTMesto{" +
                "tMesto=" + tMesto +
                ", kPl=" + kPl +
                ", kGp=" + kGp +
                ", ntMesto='" + ntMesto + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBAccordOrderTMesto that = (DBAccordOrderTMesto) o;
        return tMesto == that.tMesto &&
                kPl == that.kPl &&
                kGp == that.kGp &&
                Objects.equals(ntMesto, that.ntMesto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tMesto, kPl, kGp, ntMesto);
    }
}
