package ir.alefmordad.tele.core.dao;

import ir.alefmordad.tele.core.entities.Entity;

import java.sql.*;

public abstract class Dao<T extends Entity<I>, I> {

    protected Connection connection;
    protected PreparedStatement ps;
    protected ResultSet rs;
    private String connectionUrl = "jdbc:mysql://localhost:3306/tele?user=root&password=";

    {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionUrl);
        } catch (Exception e) {
        }
    }

    public abstract void create(T object) throws SQLException;

    public abstract T read(I id) throws SQLException;

    public abstract void update(T object) throws SQLException;

    public abstract void delete(I id) throws SQLException;

    @Override
    protected void finalize() {
        try {
            ps.close();
            connection.close();
        } catch (Exception e) {
        }
    }

}
