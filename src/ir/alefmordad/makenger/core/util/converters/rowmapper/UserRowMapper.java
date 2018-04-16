package ir.alefmordad.makenger.core.util.converters.rowmapper;

import ir.alefmordad.makenger.core.entities.User;


import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User convert(ResultSet rs) throws SQLException {
        User user = new User();
        if (!rs.next())
            return null;
        user.setId(rs.getString(1));
        user.setPassword(rs.getString(2));
        return user;
    }
}
