package ru.javastudy.mvcHtml5Angular.mvc.service;

import ru.javastudy.mvcHtml5Angular.mvc.rest.model.DBAccordOrderRs1;
import ru.javastudy.mvcHtml5Angular.mvc.rest.model.DBAccordOrderRs2;

import java.util.List;

public interface JBDCAccordDAORs1 {
    DBAccordOrderRs1 findByTtn(int ttn);
    void save(DBAccordOrderRs1 orderRs1);
    int update(DBAccordOrderRs1 orderRs1);
    int delete(int ttn);
    List<DBAccordOrderRs2> findRs2ByTtn(int ttn);
    List<DBAccordOrderRs1> findAll();
}
