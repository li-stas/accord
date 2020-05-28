package accord.mvc.service;

import accord.mvc.model.DBAccordOrderTMesto;

public interface JBDCAccordDAOTMesto {
    DBAccordOrderTMesto findByTMesto(int numTMesto);
    DBAccordOrderTMesto findByTMesto4KplKGp(int numKPl, int numKGp);
}
