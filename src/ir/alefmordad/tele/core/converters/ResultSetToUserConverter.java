package ir.alefmordad.tele.core.converters;

import ir.alefmordad.tele.core.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToUserConverter implements Converter<ResultSet, User> {

    @Override
    public User convert(ResultSet rs) throws SQLException {
        User user = new User();
        rs.next();
        user.setId(rs.getString(1));
        return user;
    }
}
