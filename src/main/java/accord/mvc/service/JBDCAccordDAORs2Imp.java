package accord.mvc.service;

import accord.mvc.rest.model.DBAccordOrderRs2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class JBDCAccordDAORs2Imp implements JBDCAccordDAORs2 {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JBDCAccordDAORs2Imp(DataSource dataAccordSource) {
        this.jdbcTemplate = new JdbcTemplate(dataAccordSource);
    }

    @Override
    public void save(DBAccordOrderRs2 oRs2) {

    }

    @Override
    public int update(DBAccordOrderRs2 oRs2) {
        System.out.println("JBDCAccordDAORs2 update is called");
        System.out.println("ORs" + oRs2);

        final String UPDATE_SQL = "UPDATE AO_rs2 " +
                "SET  Kvp = ?, Zen = ? " +
                "WHERE ttn LIKE ? and MnTov LIKE ?";

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
    public void delete(DBAccordOrderRs2 oRs2) {

    }
}
