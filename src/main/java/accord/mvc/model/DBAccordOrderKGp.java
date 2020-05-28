package accord.mvc.model;

public class DBAccordOrderKGp {
    private int kGp;// NUMERIC(7)  NOT NuLL,
    private String nGrpol;// VARCHAR(90) NOT NuLL,
    private String adr;// VARCHAR(200)  NOT NuLL,

    public DBAccordOrderKGp() {
    }

    public int getkGp() {
        return kGp;
    }

    public void setkGp(int kGp) {
        this.kGp = kGp;
    }

    public String getnGrpol() {
        return nGrpol;
    }

    public void setnGrpol(String nGrpol) {
        this.nGrpol = nGrpol;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    @Override
    public String toString() {
        return "DBAccordOrderKGp{" +
                "kGp=" + kGp +
                ", nGrpol='" + nGrpol + '\'' +
                '}';
    }
}
