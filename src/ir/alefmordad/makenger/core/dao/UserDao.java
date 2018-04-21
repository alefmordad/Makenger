package ir.alefmordad.makenger.core.dao;

import ir.alefmordad.makenger.core.entities.User;
import ir.alefmordad.makenger.core.util.converters.rowmapper.UserRowMapper;
import ir.alefmordad.makenger.core.util.converters.rowmapper.UsersListRowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao extends Dao<User, String> {

    private static UserDao instance;

    private UserDao() {
    }

    @Override
    public void create(User object) throws SQLException {
        String query = "INSERT INTO users(id,password) VALUES(?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, object.getId());
        ps.setString(2, object.getPassword());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public User read(String id) throws SQLException {
        String query = "SELECT * FROM users WHERE id=? AND deleted=0";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        User user = new UserRowMapper().convert(rs);
        rs.close();
        ps.close();
        return user;
    }

    @Override
    public void update(User user) throws SQLException {
        String query = "UPDATE users SET password=? WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, user.getPassword());
        ps.setString(2, user.getId());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(User user) throws SQLException {
        String query = "UPDATE users SET deleted=? WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setBoolean(1, user.getDeleted());
        ps.setString(2, user.getId());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public List<User> list(User groupUsers) throws SQLException {
        String query = "SELECT * FROM groupUsers AS gu JOIN users ON users.id=gu.user_id WHERE gu.id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, groupUsers.getId());
        ResultSet rs = ps.executeQuery();
        List<User> users = new UsersListRowMapper().convert(rs);
        rs.close();
        ps.close();
        return users;
    }

    public static UserDao getInstance() {
        if (instance == null)
            instance = new UserDao();
        return instance;
    }
}
