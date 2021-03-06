package com.apress.prospring5.ch6.operation;

import com.apress.prospring5.ch6.entities.Singer;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component("selectAllSingersOperation")
public class SelectAllSingersOperation extends MappingSqlQuery<Singer> {
    private static final String SQL_SELECT_ALL_SINGERS = "select id, first_name, last_name, birth_date from singer";

    public SelectAllSingersOperation(DataSource dataSource) {
        super(dataSource, SQL_SELECT_ALL_SINGERS);
    }

    @Override
    protected Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Singer singer = new Singer();
        singer.setId(rs.getLong("id"));
        singer.setFirstName(rs.getString("first_name"));
        singer.setLastName(rs.getString("last_name"));
        singer.setBirthDate(rs.getDate("birth_date"));
        return singer;
    }
}
