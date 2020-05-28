package accord.mvc.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

/**
 * струкутура с избыточными данными. повторяется шапка в каждой строке товара
 */

public class DBAccordOrderRs1AndRs2 {
    // шапка заказа
    private int ttn;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dvp;
    private int tMesto;
    private int kta;
    // строки товар
    private int mnTov;
    private float kvp;
    private float zen;

    public DBAccordOrderRs1AndRs2() {
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
        return "OrderRs1AndRs2{" +
                "ttn=" + ttn +
                ", tMesto=" + tMesto +
                ", kta=" + kta +
                ", mnTov=" + mnTov +
                ", kvp=" + kvp +
                ", zen=" + zen +
                '}';
    }
}
