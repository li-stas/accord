package accord.mvc.service;

import accord.mvc.model.DBAccordOrderKGp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JBDCAccordDAOKGpImp implements JBDCAccordDAOKGp {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JBDCAccordDAOKGpImp(DataSource dataAccordSource) {
        this.jdbcTemplate = new JdbcTemplate(dataAccordSource);
    }
    @Override
    public DBAccordOrderKGp findByKGp(int numKGp) {
        System.out.println("DBAccordOrderKGp findByKGp is called=" + numKGp);
        final String querySQL = "SELECT AO_kgp.* FROM AO_KGP WHERE  KGP = ?";

        return jdbcTemplate.queryForObject(
                querySQL, new Object[]{numKGp}, new BeanPropertyRowMapper<>(DBAccordOrderKGp.class));
    }

    @Override
    public List<DBAccordOrderKGp> oListKGp4KPl(int numKta, int numKPl) {
        System.out.println("DBAccordOrderKGp oListKGp4KPl is called");
        final String querySQL = "SELECT AO_kgp.* "
                + " FROM AO_KGP, AO_STAGTM STagTm, AO_TMESTO tm "
                + " WHERE AO_KGP.KGP = tm.KGP"
                + " and tm.KPL=" + numKPl
                + " and tm.TMESTO = STagTm.TMESTO"
                + " and STagTm.KTA = " + numKta
                + " ORDER BY AO_kgp.NGRPOL ASC";
        System.out.println("querySQL=" + querySQL);
        List<DBAccordOrderKGp> listKGp
                = jdbcTemplate.query(querySQL, new RowMapper<DBAccordOrderKGp>() {
            @Override
            public DBAccordOrderKGp mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                DBAccordOrderKGp oRec = new DBAccordOrderKGp();
                oRec.setkGp(resultSet.getInt("KGP"));
                oRec.setnGrpol(resultSet.getString("NGRPOL"));
                return oRec;
            }
        });
        return listKGp;
    }
    
    @Override
    public List<DBAccordOrderKGp> selectStartsWith(int numKta, String name) {
        System.out.println("DBAccordOrderKGp selectStartsWith-01 is called");
        name = name.toUpperCase();
        final String querySQL =
                "SELECT AO_KGp.* " +
                        " FROM AO_KGp, AO_STAGTM STagTm, AO_TMESTO tm "
                        + " WHERE AO_kgp.KGP = tm.KGP"
                        + " and tm.TMESTO = STagTm.TMESTO"
                        + " and STagTm.KTA = " + numKta
                        + " and UPPER(AO_kgp.NGRPOL) LIKE '" + name + "%'"
                        + " ORDER BY AO_kgp.NGRPOL ASC";
        List<DBAccordOrderKGp> listKPl
                = jdbcTemplate.query(querySQL, new RowMapper<DBAccordOrderKGp>() {
            @Override
            public DBAccordOrderKGp mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                DBAccordOrderKGp oRec = new DBAccordOrderKGp();
                oRec.setkGp(resultSet.getInt("KGP"));
                oRec.setnGrpol(resultSet.getString("NGRPOL"));
                oRec.setAdr("ADR");
                return oRec;
            }
        });
        return listKPl;
    }

    @Override
    public List<DBAccordOrderKGp> selectStartsWith(int numKta, int numKPl, String name) {
        System.out.println("DBAccordOrderKGp selectStartsWith-02 is called");
        name = name.toUpperCase();
        final String querySQL =
                "SELECT AO_KGp.* " +
                        " FROM AO_KGp, AO_STAGTM STagTm, AO_TMESTO tm "
                        + " WHERE AO_kgp.KGP = tm.KGP"
                        + " and tm.KPL = " + numKPl
                        + " and tm.TMESTO = STagTm.TMESTO"
                        + " and STagTm.KTA = " + numKta
                        + " and UPPER(AO_kgp.NGRPOL) LIKE '" + name + "%'"
                        + " ORDER BY AO_kgp.NGRPOL ASC";
        List<DBAccordOrderKGp> listKPl
                = jdbcTemplate.query(querySQL, new RowMapper<DBAccordOrderKGp>() {
            @Override
            public DBAccordOrderKGp mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                DBAccordOrderKGp oRec = new DBAccordOrderKGp();
                oRec.setkGp(resultSet.getInt("KGP"));
                oRec.setnGrpol(resultSet.getString("NGRPOL"));
                oRec.setAdr("ADR");
                return oRec;
            }
        });
        return listKPl;
    }


}
