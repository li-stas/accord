package ru.javastudy.mvcHtml5Angular.mvc.rest.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * струкутура с избыточными данными. повторяется шапка в каждой строке товара
 */

public  class DBAccordOrderRs1 {
    // шапка заказа
    private int ttn;
    private LocalDateTime dvp;
    private int tMesto;
    private int kta;
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

    public void setListRs2(List<DBAccordOrderRs2> listRs2) {
        this.listRs2 = listRs2;
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
                ", listRs2=" + listRs2 +
                '}';
    }

}



