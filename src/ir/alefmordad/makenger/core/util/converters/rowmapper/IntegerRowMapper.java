package ir.alefmordad.makenger.core.util.converters.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerRowMapper implements RowMapper<Integer> {

    @Override
    public Integer convert(ResultSet from) throws SQLException {
        from.next();
        return from.getInt(1);
    }

}
