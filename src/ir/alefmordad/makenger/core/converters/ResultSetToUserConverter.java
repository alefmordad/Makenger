package ir.alefmordad.makenger.core.converters;

import ir.alefmordad.makenger.core.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToUserConverter implements Converter<ResultSet, User> {

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
