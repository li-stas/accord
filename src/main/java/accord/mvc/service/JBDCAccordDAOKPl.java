package accord.mvc.service;

import accord.mvc.model.DBAccordOrderKPl;

import java.util.List;

public interface JBDCAccordDAOKPl {
    DBAccordOrderKPl findByKPl(int numKPl);
    List<DBAccordOrderKPl> selectStartsWith(int numKta, String name);
    List<DBAccordOrderKPl> oListKGp4KGp(int numKta, int numKGP);
}
