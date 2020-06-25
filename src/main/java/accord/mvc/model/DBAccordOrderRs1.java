package accord.mvc.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * струкутура с избыточными данными. повторяется шапка в каждой строке товара
 */

public  class DBAccordOrderRs1 implements Serializable {
    // шапка заказа
    private int ttn;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dvp;
    private double sdv;
    private int tMesto;
    private String ntMesto;
    private int kta;
    private int prz;
    private List<DBAccordOrderRs2> listRs2;


    public DBAccordOrderRs1() {
    }

    public String getNtMesto() {
        return ntMesto;
    }

    public void setNtMesto(String ntMesto) {
        this.ntMesto = ntMesto;
    }

    public int getTtn() {
        return ttn;
    }

    public void setTtn(int ttn) {
        this.ttn = ttn;
    }

    public LocalDateTime getDvp() {
        return dvp;
    }

    public void setDvp(LocalDateTime dvp) {
        this.dvp = dvp;
    }

    public int getTMesto() {
        return tMesto;
    }

    public void setTMesto(int tMesto) {
        this.tMesto = tMesto;
    }

    public int gettMesto() {
        return tMesto;
    }

    public int getKta() {
        return kta;
    }

    public void setKta(int kta) {
        this.kta = kta;
    }

    public int getPrz() {
        return prz;
    }

    public void setPrz(int prz) {
        this.prz = prz;
    }

    public double getSdv() {
        return sdv;
    }

    public void setSdv(double sdv) {
        this.sdv = sdv;
    }

    public void setListRs2(List<DBAccordOrderRs2> listRs2) {
        this.listRs2 = listRs2;
        this.sdv = 0;
        for (DBAccordOrderRs2 oRs2: listRs2) {
            double svp = BigDecimal.valueOf(oRs2.getKvp() * oRs2.getZen()).setScale(2, RoundingMode.HALF_UP).doubleValue();
            this.sdv = BigDecimal.valueOf(this.sdv + svp).setScale(2, RoundingMode.HALF_UP).doubleValue();

        }
    }

    public List<DBAccordOrderRs2> getListRs2() {
        return listRs2;
    }

    @Override
    public String toString() {
        return "Rs1{" +
                "ttn=" + ttn +
                ", tMesto=" + tMesto +
                ", kta=" + kta +
                ", prZ=" + prz +
                ", listRs2=" + listRs2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBAccordOrderRs1 that = (DBAccordOrderRs1) o;
        return ttn == that.ttn &&
                Double.compare(that.sdv, sdv) == 0 &&
                tMesto == that.tMesto &&
                kta == that.kta &&
                prz == that.prz &&
                Objects.equals(dvp, that.dvp) &&
                Objects.equals(ntMesto, that.ntMesto) &&
                Objects.equals(listRs2, that.listRs2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ttn, dvp, sdv, tMesto, ntMesto, kta, prz, listRs2);
    }
}



