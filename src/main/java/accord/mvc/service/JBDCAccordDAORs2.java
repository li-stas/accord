package accord.mvc.service;

import accord.mvc.model.DBAccordOrderRs2;

import java.util.List;

public interface JBDCAccordDAORs2 {
    int save(DBAccordOrderRs2 oRs2);
    int update(DBAccordOrderRs2 oRs2);
    int delete(DBAccordOrderRs2 oRs2);
    List<DBAccordOrderRs2> findRs2ByTtn(int numTtn);
}
