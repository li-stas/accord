package accord.mvc.model;

import java.util.Objects;

public class DBAccordOrderKPl {
    private int kKL; // NUMERIC(7)  NOT NuLL,
    private String  nKL;// VARCHAR(90)  NOT NuLL,
    private String adr;// VARCHAR(200)  NOT NuLL,

    public DBAccordOrderKPl() {
    }

    public int getkKL() {
        return kKL;
    }

    public void setkKL(int kKL) {
        this.kKL = kKL;
    }

    public String getnKL() {
        return nKL;
    }

    public void setnKL(String nKL) {
        this.nKL = nKL;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    @Override
    public String toString() {
        return "DBAccordOrderKPl{" +
                "kKL=" + kKL +
                ", nKL='" + nKL + '\'' +
                ", adr='" + adr + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBAccordOrderKPl that = (DBAccordOrderKPl) o;
        return kKL == that.kKL;
    }

    @Override
    public int hashCode() {
        return Objects.hash(kKL);
    }
}
