package accord.mvc.service;

import accord.mvc.model.DBAccordOrderRs1;
import accord.mvc.model.DBAccordOrderRs2;

import java.util.List;

public interface JBDCAccordDAORs1 {
    DBAccordOrderRs1 findByTtn(int ttn);
    int save(DBAccordOrderRs1 orderRs1);
    int update(DBAccordOrderRs1 orderRs1);
    int delete(int ttn);
    List<DBAccordOrderRs1> findAll();
    List<DBAccordOrderRs1> getDbAccordOrderRs1s(String querySQL);
}
