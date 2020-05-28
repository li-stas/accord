package accord.mvc.service;

import accord.mvc.model.DBAccordOrderKPl;
import accord.mvc.model.DBAccordOrderKPl;
import accord.mvc.model.DBAccordOrderTov;
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
public class JBDCAccordDAOKPlImp implements JBDCAccordDAOKPl {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JBDCAccordDAOKPlImp(DataSource dataAccordSource) {
        this.jdbcTemplate = new JdbcTemplate(dataAccordSource);
    }

    @Override
    public DBAccordOrderKPl findByKPl(int numKPl) {
        System.out.println("DBAccordOrderKPl findByKPl is called");
        final String querySQL = "SELECT * FROM AO_KPl WHERE  KKL = ?";

        DBAccordOrderKPl oRec = jdbcTemplate.queryForObject(
                querySQL, new Object[]{numKPl}, new BeanPropertyRowMapper<>(DBAccordOrderKPl.class));
        return oRec;
    }

    public List<DBAccordOrderKPl> selectStartsWith(int numKta, String name) {
        System.out.println("DBAccordOrderKPl selectStartsWith is called");
        name = name.toUpperCase();
        final String querySQL =
                "SELECT kpl.KKL, kpl.NKL, kpl.ADR " +
                        " FROM AO_KPl kpl, AO_STAGTM STagTm, AO_TMESTO tm "
                        + " WHERE kpl.KKL=tm.KPL"
                        + " and tm.TMESTO=STagTm.TMESTO"
                        + " and STagTm.KTA=" + numKta
                        + " and UPPER(kpl.NKL) LIKE '" + name + "%'"
                        + " ORDER BY kpl.NKL ASC";
        List<DBAccordOrderKPl> listKPl
                = jdbcTemplate.query(querySQL, new RowMapper<DBAccordOrderKPl>() {
            @Override
            public DBAccordOrderKPl mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                DBAccordOrderKPl oRec = new DBAccordOrderKPl();
                oRec.setkKL(resultSet.getInt("KKL"));
                oRec.setnKL(resultSet.getString("NKL"));
                oRec.setAdr(resultSet.getString("ADR"));
                return oRec;
            }
        });
        return listKPl;
    }

    @Override
    public List<DBAccordOrderKPl> oListKGp4KGp(int numKta, int numKGP) {
        System.out.println("DBAccordOrderKPl oListKGp4KGp is called");
        final String querySQL =
                "SELECT kpl.* "
                +" FROM AO_KPl kpl, AO_STAGTM STagTm, AO_TMESTO tm "
                + " WHERE kpl.KKL=tm.KPL"
                + " and tm.KGP = " + numKGP
                + " and tm.TMESTO = STagTm.TMESTO"
                + " and STagTm.KTA = " + numKta
                + " ORDER BY kpl.NKL ASC";
        System.out.println("querySQL=" + querySQL);
        List<DBAccordOrderKPl> listKGp
                = jdbcTemplate.query(querySQL, new RowMapper<DBAccordOrderKPl>() {
            @Override
            public DBAccordOrderKPl mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                DBAccordOrderKPl oRec = new DBAccordOrderKPl();
                oRec.setkKL(resultSet.getInt("KKL"));
                oRec.setnKL(resultSet.getString("NKL"));
                oRec.setAdr(resultSet.getString("ADR"));
                return oRec;
            }
        });
        return listKGp;
    }
}
