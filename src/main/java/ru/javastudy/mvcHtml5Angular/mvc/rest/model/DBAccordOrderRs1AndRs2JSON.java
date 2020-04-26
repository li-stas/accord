package ru.javastudy.mvcHtml5Angular.mvc.rest.model;

/**
 * струкутура с избыточными данными. повторяется шапка в каждой строке товара
 */

public class DBAccordOrderRs1AndRs2JSON {
    // шапка заказа
    private int ttn;
    private int tMesto;
    private int kta;
    // строки товар
    private int mnTov;
    private float kvp;
    private float zen;

    public DBAccordOrderRs1AndRs2JSON() {
    }

    public int getTtn() {
        return ttn;
    }

    public void setTtn(int ttn) {
        this.ttn = ttn;
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
