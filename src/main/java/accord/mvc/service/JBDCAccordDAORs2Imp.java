package accord.mvc.service;

import accord.mvc.model.DBAccordOrderRs2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class JBDCAccordDAORs2Imp implements JBDCAccordDAORs2 {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JBDCAccordDAORs2Imp(DataSource dataAccordSource) {
        this.jdbcTemplate = new JdbcTemplate(dataAccordSource);
    }

    @Override
    public int save(DBAccordOrderRs2 oRs2) {
        System.out.println("\nJBDCAccordDAORs2 INSERT INTO is called");
        System.out.println("  ORs" + oRs2);

        final String UPDATE_SQL =
                "INSERT INTO ao_rs2 (TTN,MnTov,KVP,Zen) VALUES (?,?,?,?)";

        int result = jdbcTemplate.update(UPDATE_SQL,
                oRs2.getTtn(), oRs2.getMnTov(),oRs2.getKvp(), oRs2.getZen());
        if (result > 0) {
            System.out.printf("numTtn=%d MnTov=%d is saved:\n",oRs2.getTtn(), oRs2.getMnTov());
            return result;
        } else {
            return result;
        }
    }

    @Override
    public int update(DBAccordOrderRs2 oRs2) {
        System.out.println("JBDCAccordDAORs2 update is called");
        System.out.println("ORs" + oRs2);

        final String UPDATE_SQL = "UPDATE AO_rs2 SET  Kvp = ?, Zen = ? WHERE ttn LIKE ? and MnTov LIKE ?";

        int result = jdbcTemplate.update(UPDATE_SQL,
                oRs2.getKvp(), oRs2.getZen(), oRs2.getTtn(), oRs2.getMnTov());
        if (result > 0) {
            System.out.printf("numTtn=%d MnTov=%d is update:\n",oRs2.getTtn(), oRs2.getMnTov());
            return result;
        } else {
            return result;
        }
    }

    @Override
    public int delete(DBAccordOrderRs2 oRs2) {
        System.out.printf("JDBCOrderRs2: delete called nomTtn=%d MnTov=%d\n", oRs2.getTtn(), oRs2.getMnTov());

        final String DELETE_SQL = "DELETE FROM AO_Rs2 WHERE ttn LIKE ? and MnTov LIKE ?";
        int result = jdbcTemplate.update(DELETE_SQL, oRs2.getTtn(), oRs2.getMnTov());
        System.out.println("result = " + result);
        if (result > 0) {
            System.out.printf("  nomTtn=%d nMnTov=%d is deleted\n", oRs2.getTtn(), oRs2.getMnTov());
            return result;
        } else {
            return result;
        }
    }

    @Override
    public List<DBAccordOrderRs2> findRs2ByTtn(int numTtn) {
        System.out.println("DBAccordOrderdService queryOrderRs2() is called");
        final String querySQL =
                "SELECT rs2.MnTov, tov.Nat, kvp, Zen"
                        + " FROM AO_RS2 rs2, AO_TOV tov"
                        + " WHERE rs2.MnTov = tov.MnTov and ttn = " + numTtn
                        + " ORDER BY tov.Nat";
        List<DBAccordOrderRs2> listRs2
                = jdbcTemplate.query(querySQL, new RowMapper<DBAccordOrderRs2>() {
            @Override
            public DBAccordOrderRs2 mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                DBAccordOrderRs2 recordRs2 = new DBAccordOrderRs2();
                recordRs2.setMnTov(resultSet.getInt("MNTOV"));
                recordRs2.setNat(resultSet.getString("NAT"));
                recordRs2.setKvp(resultSet.getFloat("KVP"));
                recordRs2.setZen(resultSet.getFloat("ZEN"));
                return recordRs2;
            }
        });
        return listRs2;
    }
}
