package accord.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class AuthenticationFacadeImp implements AuthenticationFacade {
    private Integer numKta;
    private String userName;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthenticationFacadeImp(DataSource dataAccordSource) {
        this.jdbcTemplate = new JdbcTemplate(dataAccordSource);
    }
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public int getNumKta() {
        String userCurName = getAuthentication().getName();
        if (numKta == null || !(userName.equals(userCurName))) {
            userName = userCurName;
            String sql = "SELECT kta FROM AO_USER WHERE USERNAME = ?";
            numKta =  jdbcTemplate.queryForObject(
                    sql, new Object[]{userName}, Integer.class);
            return numKta;
        } else {

            return numKta;
        }

    }
}
