package eu.laytin.projectbytz1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PersonDAO {
    private final JdbcTemplate jbdc;

    @Autowired
    public PersonDAO(JdbcTemplate jbdc) {
        this.jbdc = jbdc;
    }





}
