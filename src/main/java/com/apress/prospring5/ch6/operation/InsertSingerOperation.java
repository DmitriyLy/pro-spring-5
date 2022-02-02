package com.apress.prospring5.ch6.operation;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Types;

@Component
public class InsertSingerOperation extends SqlUpdate {
    private static final String SQL_INSERT_SINGER = "insert into singer (first_name, last_name, birth_date) " +
            "values(:first_name, :last_name, :birth_date)";

    public InsertSingerOperation(DataSource dataSource) {
        super(dataSource, SQL_INSERT_SINGER);
        declareParameter(new SqlParameter("first_name", Types.VARCHAR));
        declareParameter(new SqlParameter("last_name", Types.VARCHAR));
        declareParameter(new SqlParameter("birth_date", Types.DATE));
        setGeneratedKeysColumnNames("id");
        setReturnGeneratedKeys(true);
    }
}
