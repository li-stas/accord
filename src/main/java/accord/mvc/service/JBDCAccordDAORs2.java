package accord.mvc.service;

import accord.mvc.rest.model.DBAccordOrderRs1;

public interface JBDCAccordDAORs2 {
    void save(DBAccordOrderRs1 dept);
    void update(DBAccordOrderRs1 dept);
    void delete(DBAccordOrderRs1 dept);
}
