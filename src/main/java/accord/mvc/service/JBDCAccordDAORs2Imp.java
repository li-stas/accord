package accord.mvc.service;

import accord.mvc.model.DBAccordOrderRs2;
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
}
