package accord.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import accord.mvc.model.DBAccordOrderRs1;
import accord.mvc.model.DBAccordOrderRs2;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class JBDCAccordDAORs1Imp implements JBDCAccordDAORs1  {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JBDCAccordDAORs1Imp(DataSource dataAccordSource) {
        this.jdbcTemplate = new JdbcTemplate(dataAccordSource);
    }

    @Override
    public DBAccordOrderRs1 findByTtn(int numTtn) {
        System.out.println("JBDCAccordDAORs1 findByTtn is called");
        final String querySQL = "SELECT ttn, dvp, rs1.tMesto, tm.ntMesto, kta" +
                " FROM AO_RS1 rs1, AO_TMesto tm" +
                " WHERE rs1.tMesto = tm.tMesto and  ttn = " + numTtn;
        DBAccordOrderRs1 recRs1 = getDbAccordOrderRs1(querySQL);
        return recRs1;
    }

    private DBAccordOrderRs1 getDbAccordOrderRs1(String querySQL) {
        List<DBAccordOrderRs1> dbAccordOrderRs2 =
                jdbcTemplate.query(querySQL, new RowMapper<DBAccordOrderRs1>() {
            @Override
            public DBAccordOrderRs1 mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                DBAccordOrderRs1 recordRs1 = new DBAccordOrderRs1();
                recordRs1.setTtn(resultSet.getInt("TTN"));
                recordRs1.setDvp(resultSet.getTimestamp("DVP").toLocalDateTime());
                recordRs1.setTMesto(resultSet.getInt("TMESTO"));
                recordRs1.setNtMesto(resultSet.getString("NTMESTO"));
                recordRs1.setKta(resultSet.getInt("KTA"));

                List<DBAccordOrderRs2> listRs2 = findRs2ByTtn(resultSet.getInt("TTN"));
                System.out.println("listRs2 = " + listRs2);
                recordRs1.setListRs2(listRs2);
                return recordRs1;
            }
        });
        return dbAccordOrderRs2.get(0);
    }

    @Override
    public int save(DBAccordOrderRs1 orderRs1) {
        System.out.println("JBDCAccordDAORs1: save is called");
        int numTtn = getSequence("ao_sq_rs1");
        orderRs1.setTtn(numTtn);

        final String INSERT_SQL = "INSERT INTO ao_rs1 (ttn, DVP, TMesto, kta, prZ) VALUES (?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(INSERT_SQL,
                  orderRs1.getTtn(),
                  Timestamp.valueOf(orderRs1.getDvp()), orderRs1.getTMesto(), orderRs1.getKta(),
                orderRs1.getPrz());
        if (result > 0) {
            System.out.println("numTtn is update: " + orderRs1.getTtn());
            return result;
        } else {
            return result;
        }
    }

    @Override
    public int update(DBAccordOrderRs1 orderRs1) {
        System.out.println("JBDCAccordDAORs1 update is called");
        //final String UPDATE_SQL = "UPDATE AO_rs1 SET DVP = ?, TMESTO = ?, KTA = ? WHERE USERNAME = ?";
        final String UPDATE_SQL = "UPDATE AO_rs1 SET DVP = ?, TMESTO = ?, KTA = ? WHERE ttn = ?";

        int result = jdbcTemplate.update(UPDATE_SQL,
                Timestamp.valueOf(orderRs1.getDvp()), orderRs1.getTMesto(), orderRs1.getKta(), orderRs1.getTtn());
        if (result > 0) {
            System.out.println("numTtn is update: " + orderRs1.getTtn());
            return result;
        } else {
            return result;
        }
    }

    @Override
    public int delete(int nomTtn) {
        System.out.println("JDBCOrderRs1: delete called nomTtn=" + nomTtn);

        final String DELETE_SQL = "DELETE FROM AO_Rs1 WHERE ttn LIKE ?";
        int result = jdbcTemplate.update(DELETE_SQL, nomTtn);
        System.out.println("r" + result);
        if (result > 0) {
            System.out.println("nomTtn is deleted: " + nomTtn);
            return result;
        } else {
            return result;
        }
    }

    @Override
    public List<DBAccordOrderRs2> findRs2ByTtn(int numTtn) {
        System.out.println("DBAccordOrderdService queryOrderRs2() is called");
        final String querySQL = "SELECT MnTov, kvp, Zen FROM AO_RS2 rs2 WHERE  ttn =" + numTtn;
        List<DBAccordOrderRs2> listRs2
                = jdbcTemplate.query(querySQL, new RowMapper<DBAccordOrderRs2>() {
            @Override
            public DBAccordOrderRs2 mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                DBAccordOrderRs2 recordRs2 = new DBAccordOrderRs2();
                recordRs2.setMnTov(resultSet.getInt("MNTOV"));
                recordRs2.setKvp(resultSet.getFloat("KVP"));
                recordRs2.setZen(resultSet.getFloat("ZEN"));
                return recordRs2;
            }
        });
        return listRs2;
    }

    @Override
    public List<DBAccordOrderRs1> findAll() {
        return null;
    }

    private Integer getSequence(String aoSqRs1) {
        Integer seq;
        String sql = "SELECT " + aoSqRs1 + ".NEXTVAL FROM dual";
        seq = jdbcTemplate.queryForObject(sql, new Object[] {}, Integer.class);
       /* String sql = "SELECT ?.NEXTVAL FROM dual";
        seq = jdbcTemplate.queryForObject(sql, new Object[] {aoSqRs1}, Integer.class);*/
        return seq;
    }

    /*
    Raw use of parameterized class 'BeanPropertyRowMapper'
    Unchecked assignment: 'org.springframework.jdbc.core.BeanPropertyRowMapper' to
    'org.springframework.jdbc.core.RowMapper<java.lang.Object>'
    Unchecked call to 'BeanPropertyRowMapper(Class<T>)' as a member of raw type
    'org.springframework.jdbc.core.BeanPropertyRowMapper'

     ERROR RestAccordController - Failed to convert property value of type [java.sql.Timestamp]
     to required type [java.time.LocalDateTime] for property 'dvp';
     nested exception is java.lang.IllegalStateException: Cannot convert value of type [java.sql.Timestamp] to
     required type [java.time.LocalDateTime] for property 'dvp': no matching editors or conversion strategy found


    private DBAccordOrderRs1 getDbAccordOrderRs102(String querySQL) {

        DBAccordOrderRs1 recRs1 = (DBAccordOrderRs1) jdbcTemplate.queryForObject(
                querySQL, new Object[]{},
                new BeanPropertyRowMapper(DBAccordOrderRs1.class));

        List<DBAccordOrderRs2> listRs2 = findRs2ByTtn(recRs1.getTtn());
        System.out.println("listRs2 = " + listRs2);
        recRs1.setListRs2(listRs2);

        return recRs1;
    }
  */
}


