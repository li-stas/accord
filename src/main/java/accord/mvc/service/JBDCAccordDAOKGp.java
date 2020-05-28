package accord.mvc.service;

import accord.mvc.model.DBAccordOrderKGp;

import java.util.List;

public interface JBDCAccordDAOKGp {
    DBAccordOrderKGp findByKGp(int numKGp);

    List<DBAccordOrderKGp> oListKGp4KPl(int numKta, int numKPl);

    List<DBAccordOrderKGp> selectStartsWith(int numKta, String name);

    List<DBAccordOrderKGp> selectStartsWith(int numKta, int numKPl, String name);
}
