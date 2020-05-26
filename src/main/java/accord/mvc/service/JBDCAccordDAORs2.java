package accord.mvc.service;

import accord.mvc.rest.model.DBAccordOrderRs2;

public interface JBDCAccordDAORs2 {
    void save(DBAccordOrderRs2 oRs2);
    int update(DBAccordOrderRs2 oRs2);
    void delete(DBAccordOrderRs2 oRs2);
}
