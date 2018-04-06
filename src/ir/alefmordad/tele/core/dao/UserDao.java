package ir.alefmordad.tele.core.dao;

import ir.alefmordad.tele.core.converters.ResultSetToUserConverter;
import ir.alefmordad.tele.core.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends Dao<User, String> {

    private static UserDao instance;

    private UserDao() {
    }

    @Override
    public void create(User object) throws SQLException {
        User user = read(object.getId());
        if (user == null) {
            String query = "insert into users(id) values(?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, object.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public User read(String id) throws SQLException {
        String query = "select * from users where id=?";
        ps = connection.prepareStatement(query);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        User user = new ResultSetToUserConverter().convert(rs);
        rs.close();
        return user;
    }

    @Override
    public void update(User user) throws SQLException {
    }

    @Override
    public void delete(String id) throws SQLException {
        String query = "delete from users where id=?";
        ps = connection.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    public static UserDao getInstance() {
        if (instance == null)
            instance = new UserDao();
        return instance;
    }
}
