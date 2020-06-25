package accord.mvc.service;

import accord.mvc.model.DBAccordOrderTMesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class JBDCAccordDAOTMestoImp implements JBDCAccordDAOTMesto {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JBDCAccordDAOTMestoImp(DataSource dataAccordSource) {
        this.jdbcTemplate = new JdbcTemplate(dataAccordSource);
    }

    @Override
    public DBAccordOrderTMesto findByTMesto(int numTMesto) {
        System.out.println("DBAccordOrderTMesto findByTMesto is called");
        final String querySQL = "SELECT * FROM AO_TMesto tm WHERE  TMesto = ?";

        DBAccordOrderTMesto oRec = jdbcTemplate.queryForObject(
                querySQL, new Object[]{numTMesto}, new BeanPropertyRowMapper<>(DBAccordOrderTMesto.class));

        return oRec;
    }

    @Override
    public DBAccordOrderTMesto findByTMesto4KplKGp(int numKPl, int numKGp) {
        System.out.println("DBAccordOrderTMesto findByTMesto4KplKGp is called");
        final String querySQL = "SELECT * FROM AO_TMesto tm WHERE KPl = ? and  KGp =?";
        DBAccordOrderTMesto oRec = jdbcTemplate.queryForObject(
                querySQL, new Object[]{numKPl, numKGp}, new BeanPropertyRowMapper<>(DBAccordOrderTMesto.class));

        return oRec;
    }
}
