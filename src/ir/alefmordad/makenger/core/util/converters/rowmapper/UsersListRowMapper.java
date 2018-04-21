package ir.alefmordad.makenger.core.util.converters.rowmapper;

import ir.alefmordad.makenger.core.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersListRowMapper implements RowMapper<List<User>> {
    @Override
    public List<User> convert(ResultSet from) throws SQLException {
        List<User> users = new ArrayList<>();
        User user = new User();
        while (from.next()) {
            user.setId(from.getString(3));
            user.setPassword(from.getString(4));
            user.setIsGroup(from.getBoolean(5));
            users.add(user);
        }
        return users;
    }
}
