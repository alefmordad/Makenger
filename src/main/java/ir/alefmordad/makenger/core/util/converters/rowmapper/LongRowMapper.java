package ir.alefmordad.makenger.core.util.converters.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LongRowMapper implements RowMapper<Long> {

    @Override
    public Long convert(ResultSet from) throws SQLException {
        from.next();
        return from.getLong(1);
    }

}
