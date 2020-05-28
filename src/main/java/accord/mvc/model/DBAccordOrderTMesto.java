package accord.mvc.model;

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
}
