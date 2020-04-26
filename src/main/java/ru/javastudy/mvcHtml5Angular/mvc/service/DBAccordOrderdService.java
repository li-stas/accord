package ru.javastudy.mvcHtml5Angular.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.javastudy.mvcHtml5Angular.mvc.rest.model.DBAccordOrderRs1AndRs2JSON;

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
    public List<DBAccordOrderRs1AndRs2JSON> queryOrderAccordRs1AndRs2JDBC2JSON() {
        System.out.println("DBLogService queryAllDBLogsJDBCExampleJSON() is called");
        final String querySQL =
                "SELECT rs1.ttn, TMesto, kta, MnTov, kvp, Zen"
                + " FROM AO_RS1 rs1, AO_rs2 rs2"
                + " WHERE rs1.ttn = rs2.ttn and kta = 364";
        List <DBAccordOrderRs1AndRs2JSON> dbAccordOrderRs1AndRs2JSONS = jdbcTemplate.query(querySQL, new RowMapper<DBAccordOrderRs1AndRs2JSON>() {
            @Override
            public DBAccordOrderRs1AndRs2JSON mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                DBAccordOrderRs1AndRs2JSON dbAccordOrderRs1AndRs2JSON = new DBAccordOrderRs1AndRs2JSON();
                dbAccordOrderRs1AndRs2JSON.setTtn(resultSet.getInt("TTN"));
                dbAccordOrderRs1AndRs2JSON.setTMesto(resultSet.getInt("TMESTO"));
                dbAccordOrderRs1AndRs2JSON.setKta(resultSet.getInt("KTA"));
                dbAccordOrderRs1AndRs2JSON.setMnTov(resultSet.getInt("MNTOV"));
                dbAccordOrderRs1AndRs2JSON.setKvp(resultSet.getFloat("KVP"));
                dbAccordOrderRs1AndRs2JSON.setZen(resultSet.getFloat("ZEN"));
                return dbAccordOrderRs1AndRs2JSON;
            }
        });
        return dbAccordOrderRs1AndRs2JSONS;
    }

}
