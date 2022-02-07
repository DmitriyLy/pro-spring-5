package com.apress.prospring5.ch6.functions;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Types;

@Component
public class GetFirstNameByIdStoredFunction extends SqlFunction<String> {
    private static final String QUERY = "select get_first_name_by_id(?)";

    public GetFirstNameByIdStoredFunction(DataSource dataSource) {
        super(dataSource, QUERY);
        declareParameter(new SqlParameter(Types.INTEGER));
        compile();
    }
}
