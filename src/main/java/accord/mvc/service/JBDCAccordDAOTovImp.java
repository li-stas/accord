package accord.mvc.service;

import accord.mvc.rest.model.DBAccordOrderTov;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class JBDCAccordDAOTovImp implements JBDCAccordDAOTov {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JBDCAccordDAOTovImp(DataSource dataAccordSource) {
        this.jdbcTemplate = new JdbcTemplate(dataAccordSource);
    }

    @Override
    public List<DBAccordOrderTov> selectStartsWith(String name) {

        System.out.println("DBAccordOrderdService queryOrderTov() is called");
        name = name.toUpperCase();
        final String querySQL =
                "SELECT MnTov, Nat, NEI, OSFO, CenPr" +
                        " FROM AO_Tov tov" +
                        " WHERE UPPER(Nat) LIKE '"+ name+"%'" +
                        " ORDER BY NAT ASC";
        List<DBAccordOrderTov> listTov
                = jdbcTemplate.query(querySQL, new RowMapper<DBAccordOrderTov>() {
            @Override
            public DBAccordOrderTov mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                DBAccordOrderTov oRec = new DBAccordOrderTov();
                oRec.setMnTov(resultSet.getInt("MNTOV"));
                oRec.setNat(resultSet.getString("NAT"));
                oRec.setNei(resultSet.getString("NEI"));
                oRec.setOsFo(resultSet.getDouble("OSFO"));
                oRec.setCenPr(resultSet.getDouble("CENPR"));
                return oRec;
            }
        });
        return listTov;
    }
    public List<DBAccordOrderTov> selectStartsWith01(String name) {

        System.out.println("DBAccordOrderdService queryOrderTov() is called");
        name = name.toUpperCase();
        final String querySQL =
                "SELECT MnTov, Nat, OsFo, NEI, CenPr" +
                        " FROM AO_Tov tov" +
                        " WHERE UPPER(Nat) LIKE '"+ name+"%'"; /*+
                        " ORDER BY NAT ASC";*/
        List<DBAccordOrderTov> listTov
                = jdbcTemplate.query(querySQL, new RowMapper<DBAccordOrderTov>() {
            @Override
            public DBAccordOrderTov mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                DBAccordOrderTov oRec = new DBAccordOrderTov();
                oRec.setMnTov(resultSet.getInt("MNTOV"));
                oRec.setNat(resultSet.getString("NAT"));
                oRec.setNei(resultSet.getString("NEI"));
                oRec.setOsFo(resultSet.getDouble("OSFO"));
                oRec.setCenPr(resultSet.getDouble("СENPR"));
                return oRec;
            }
        });
        return listTov;
    }
}
