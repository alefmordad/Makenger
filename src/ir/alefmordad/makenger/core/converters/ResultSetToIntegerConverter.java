package ir.alefmordad.makenger.core.converters;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToIntegerConverter implements Converter<ResultSet, Integer> {

    @Override
    public Integer convert(ResultSet from) throws SQLException {
        from.next();
        return from.getInt(1);
    }

}
