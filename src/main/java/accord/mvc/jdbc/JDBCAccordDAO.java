package accord.mvc.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import org.springframework.jdbc.datasource.init.ScriptUtils.*;

/**
 * Created for JavaStudy.ru on 24.02.2016.
 */
@Repository
public class JDBCAccordDAO {

    private JdbcTemplate jdbcTemplate;
    private int noOfRecords;

    @Autowired
    DataSource dataAccordSource; //look to application-context.xml bean id='dataSource' definition

    /*https://www.baeldung.com/running-setup-logic-on-startup-in-spring*/
    @PostConstruct
    public void init() {
        System.out.println("JDBCAccordExample postConstruct is called. datasource = " + dataAccordSource);
        jdbcTemplate = new JdbcTemplate(dataAccordSource);
        try {
            iniAOtable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * https://www.codota.com/code/java/methods/org.springframework.jdbc.datasource.init.ScriptUtils/executeSqlScript
     * @throws SQLException
     */
    private void iniAOtable() throws SQLException {
        Connection connection =  dataAccordSource.getConnection();
        int cntTable = getCntTable();
        aboutConn(connection);

        if (cntTable == 0) {
            try {
                ClassPathResource resFile = new ClassPathResource("ao_dbschema.sql");
                //System.out.println("ClassPathResource resFile = " + resFile);
                ScriptUtils.executeSqlScript(connection,
                        new EncodedResource(resFile, StandardCharsets.UTF_8),
                        false, false,
                        ScriptUtils.DEFAULT_COMMENT_PREFIX, "/",
                        ScriptUtils.DEFAULT_BLOCK_COMMENT_START_DELIMITER, ScriptUtils.DEFAULT_BLOCK_COMMENT_END_DELIMITER
                        );
                List<String> aSqlFile = Arrays.asList("ao_add_user.sql", "ao_add_kgp.sql", "ao_add_kpl.sql"
                        , "ao_add_rs1.sql", "ao_add_rs2.sql", "ao_add_s_tag.sql", "ao_add_stagtm.sql"
                        , "ao_add_tmesto.sql", "ao_add_tov.sql");
                for (String cSqlFile : aSqlFile) {
                    ScriptUtils.executeSqlScript(connection,
                            new EncodedResource(new ClassPathResource(cSqlFile), StandardCharsets.UTF_8));
                }
                System.out.println("Run = " + "ao_dbschema.sql");

            } finally {
                connection.close();
            }
        }
    }

    private int getCntTable() {
        Integer cntTable;
        String sql = "select count(*) as cnt from user_tables where table_name like 'AO%'";
        cntTable = jdbcTemplate.queryForObject(sql, new Object[] {}, Integer.class);
        System.out.println("cntTable = " + cntTable);
        return cntTable;
    }


    private void aboutConn(Connection connection) throws SQLException {
        DatabaseMetaData dma = connection.getMetaData();
        // Печать сообщения об успешном соединении

        System.out.println("\nJDBC - aboutConnect");
        System.out.println("  Connected to " + dma.getURL());
        System.out.println("  Driver " + dma.getDriverName());
        System.out.println("  Version " + dma.getDriverVersion());
    }



    public List<List<String>> queryRptOrder01() {
        System.out.println("JDBCAccordExample: queryRptOrder01 is called");
        final String QUERY_SQL = "SELECT s_tag.kod AS kta, s_tag.FIO AS FIO, "
                + "(SELECT SUM(rs2.KVP * rs2.ZEN) AS SumOrd FROM  AO_RS1 rs1, AO_RS2 rs2 WHERE s_tag.kod = rs1.KTA and rs1.TTN = rs2.TTN) AS SumOrd,"
                + "(SELECT COUNT(TTN) FROM AO_RS1 rs1 WHERE s_tag.kod = rs1.KTA) AS CntOrd "
                + " FROM AO_S_TAG s_tag "
                + " WHERE KTAS = 31 and KTAS <> Kod";
        List<List<String>> aRecList = jdbcTemplate.query(QUERY_SQL, new RowMapper<List<String>>() {
            public List<String> mapRow(ResultSet resultSet, int rowNum) throws SQLException {

                ResultSetMetaData md = resultSet.getMetaData();
                int cols = md.getColumnCount();
                List<String> columnList = new ArrayList<String>();
                for (int i = 0; i < cols; i++) {
                    columnList.add(resultSet.getString(i + 1));
                }

                //System.out.println("int cols = " + md.getColumnCount());
                //System.out.println("aRecList = " + columnList.toString());
                return columnList;
            }
        });
        System.out.println("aRecList = " + aRecList.toString());
        return aRecList;
    }
    public List<List<String>> queryRptTov01(int page, int recordsPerPage) {
        int offset = (page - 1) * recordsPerPage;
        int noOfRecords = recordsPerPage;

        int pageNumber = page;
        int pageSize = recordsPerPage;

        System.out.println("JDBCAccordExample: queryRptTov01 is called");

        final String QUERY_SQL = String.format("SELECT * FROM " +
                "    ( " +
                "        SELECT a.*, rownum r__ " +
                "        FROM " +
                "            ( " +
                "                SELECT tov.MNTOV AS MnTov, NAT, NEI, " +
                "                       (SELECT SUM(KVP) FROM AO_RS2 rs2 WHERE tov.MNTOV = rs2.MNTOV (+) ) AS Qt, " +
                "                       (SELECT SUM(KVP * ZEN) FROM AO_RS2 rs2 WHERE tov.MNTOV = rs2.MNTOV (+) ) AS SumTov " +
                "                FROM AO_TOV tov " +
                "                ORDER BY Nat " +
                "            ) a " +
                "        WHERE rownum < ((%d * %d) + 1 ) " +
                "    ) " +
                "WHERE r__ >= (((%d - 1) * %d) + 1)", pageNumber, pageSize, pageNumber, pageSize);
        List<List<String>> aRecList = jdbcTemplate.query(QUERY_SQL, new RowMapper<List<String>>() {
            public List<String> mapRow(ResultSet resultSet, int rowNum) throws SQLException {

                ResultSetMetaData md = resultSet.getMetaData();
                int cols = md.getColumnCount();
                List<String> columnList = new ArrayList<String>();
                for (int i = 0; i < cols; i++) {
                    columnList.add(resultSet.getString(i + 1));
                }
                //System.out.println("int cols = " + md.getColumnCount());
                //System.out.println("aRecList = " + columnList.toString());
                return columnList;
            }
        });

        // к-во записей всего макс
        final String QUERY_SQL1 = "SELECT COUNT(*) FROM AO_TOV ORDER BY Nat";
        this.noOfRecords = jdbcTemplate.queryForObject(QUERY_SQL1, new Object[] {}, Integer.class);
        /*List<Integer> aMaxRecNo = jdbcTemplate.query(QUERY_SQL1, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Integer nMaxRecNo = resultSet.getInt(1);
                return nMaxRecNo;
            }
        });
        this.noOfRecords = aMaxRecNo.get(0);*/

        //System.out.println("aRecList = " + aRecList.toString());
        return aRecList;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }


}
