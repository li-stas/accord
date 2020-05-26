package accord.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import accord.mvc.rest.model.DBAccordOrderRs1;
import accord.mvc.rest.model.DBAccordOrderRs2;
import accord.mvc.rest.model.DBAccordOrderRs1AndRs2;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class DBAccordOrderdService {
    @PersistenceContext
    private EntityManager entityManager;

    /* or you can use JDBCTemplate instead JPA */
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DBAccordOrderdService(DataSource dataAccordSource) {
        this.jdbcTemplate = new JdbcTemplate(dataAccordSource);
    }

    //JdbcTemplate query with in method RowMapper example (JSON)
    public List<DBAccordOrderRs1AndRs2> queryOrderAccordRs1AndRs2JDBC2JSON() {
        System.out.println("DBAccordOrderdService queryOrderAccordRs1AndRs2JDBC2JSON() is called");
        final String querySQL =
                "SELECT rs1.ttn, dvp, TMesto, kta, MnTov, kvp, Zen"
                + " FROM AO_RS1 rs1, AO_rs2 rs2"
                + " WHERE rs1.ttn = rs2.ttn and kta = 364";
        List <DBAccordOrderRs1AndRs2> dbAccordOrderRs1AndRs2s
                = jdbcTemplate.query(querySQL, new RowMapper<DBAccordOrderRs1AndRs2>() {
            @Override
            public DBAccordOrderRs1AndRs2 mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                DBAccordOrderRs1AndRs2 recordRs1AndRs2 = new DBAccordOrderRs1AndRs2();
                recordRs1AndRs2.setTtn(resultSet.getInt("TTN"));
                recordRs1AndRs2.setDvp(resultSet.getTimestamp("DVP").toLocalDateTime());
                recordRs1AndRs2.setTMesto(resultSet.getInt("TMESTO"));
                recordRs1AndRs2.setKta(resultSet.getInt("KTA"));
                recordRs1AndRs2.setMnTov(resultSet.getInt("MNTOV"));
                recordRs1AndRs2.setKvp(resultSet.getFloat("KVP"));
                recordRs1AndRs2.setZen(resultSet.getFloat("ZEN"));
                return recordRs1AndRs2;
            }
        });
        return dbAccordOrderRs1AndRs2s;
    }

    public List<DBAccordOrderRs1> queryOrderAccordRs2InRs1JDBC2JSON(int numKta) {
        System.out.println("DBAccordOrderdService queryOrderAccordRs2InRs1JDBC2JSON() is called");
        final String querySQL = String.format("SELECT ttn, dvp, tMesto, kta FROM AO_RS1 rs1 WHERE  kta = %d", numKta);
        List <DBAccordOrderRs1> dbAccordOrderRs2InR1JSONs
                = jdbcTemplate.query(querySQL, new RowMapper<DBAccordOrderRs1>() {
            @Override
            public DBAccordOrderRs1 mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                DBAccordOrderRs1 recordRs1 = new DBAccordOrderRs1();
                recordRs1.setTtn(resultSet.getInt("TTN"));
                recordRs1.setDvp(resultSet.getTimestamp("DVP").toLocalDateTime());
                recordRs1.setTMesto(resultSet.getInt("TMESTO"));
                recordRs1.setKta(resultSet.getInt("KTA"));

                List<DBAccordOrderRs2> listRs2 = queryOrderRs2(resultSet.getInt("TTN"));
                System.out.println("listRs2 = " + listRs2);
                recordRs1.setListRs2(listRs2);
                return recordRs1;
            }
        });
        return dbAccordOrderRs2InR1JSONs;
    }

   public List<DBAccordOrderRs2> queryOrderRs2(int nomTtn) {
        System.out.println("DBAccordOrderdService queryOrderRs2() is called");
        final String querySQL =  String.format(
                "SELECT rs2.MnTov, tov.Nat, kvp, Zen " +
                "FROM AO_RS2 rs2, AO_TOV tov " +
                "WHERE rs2.MnTov = tov.MnTov and ttn = %d" +
                "ORDER BY tov.Nat",nomTtn);
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
    public int queryOrderRs1Delete(int numTtn) {
        System.out.println("JDBCOrderRs1: delete called nomTtn=" + numTtn);

        final String DELETE_SQL = "DELETE FROM AO_Rs1 WHERE ttn LIKE ?";
        int result = jdbcTemplate.update(DELETE_SQL,new Object[]{numTtn});
        System.out.println("result" + result);
        if (result > 0) {
            System.out.println("nomTtn is deleted: " + numTtn);
            return result;
        } else {
            return result;
        }
    }
    public int queryOrderRs2Delete(int numTtn, int nMnTov) {
        System.out.printf("JDBCOrderRs2: delete called nomTtn=%d MnTov=%d\n", numTtn, nMnTov);

        final String DELETE_SQL = "DELETE FROM AO_Rs2 WHERE ttn LIKE ? and MnTov LIKE ?";
        int result = jdbcTemplate.update(DELETE_SQL, numTtn, nMnTov);
        System.out.println("result = " + result);
        if (result > 0) {
            System.out.printf("  nomTtn=%d nMnTov=%d is deleted\n", numTtn, nMnTov);
            return result;
        } else {
            return result;
        }
    }
}