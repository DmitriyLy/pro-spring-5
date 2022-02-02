package com.apress.prospring5.ch6.operation;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Types;

@Component("updateSingerOperation")
public class UpdateSingerOperation extends SqlUpdate {

    private static final String SQL_UPDATE_SINGER = "update singer set first_name=:first_name, " +
            "last_name=:last_name, birth_date=:birth_date where id=:id";

    public UpdateSingerOperation(DataSource dataSource) {
        super(dataSource, SQL_UPDATE_SINGER);
        declareParameter(new SqlParameter("first_name", Types.VARCHAR));
        declareParameter(new SqlParameter("last_name", Types.VARCHAR));
        declareParameter(new SqlParameter("birth_date", Types.DATE));
        declareParameter(new SqlParameter("id", Types.INTEGER));
    }
}
