package accord.mvc.service;

import accord.mvc.rest.model.DBAccordOrderTov;

import java.util.List;

public interface JBDCAccordDAOTov {
    List<DBAccordOrderTov> selectStartsWith(String name);
}
