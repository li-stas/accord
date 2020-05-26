package accord.mvc.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

/**
 * струкутура с избыточными данными. повторяется шапка в каждой строке товара
 */

public  class DBAccordOrderRs1 {
    // шапка заказа
    private int ttn;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dvp;
    private double sdv;
    private int tMesto;
    private int kta;
    private int prz;
    private List<DBAccordOrderRs2> listRs2;


    public DBAccordOrderRs1() {
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

    public int gettMesto() {
        return tMesto;
    }
}



