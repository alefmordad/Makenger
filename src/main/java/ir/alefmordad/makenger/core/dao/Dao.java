package ir.alefmordad.makenger.core.dao;

import ir.alefmordad.makenger.core.entities.Entity;
import ir.alefmordad.makenger.core.entities.User;
import ir.alefmordad.makenger.server.Channel;

import java.sql.*;
import java.util.List;

public abstract class Dao<T extends Entity<I>, I> {

    protected Connection connection;
    private String connectionUrl = "jdbc:mysql://localhost:3306/makenger?user=root&password=";

    {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void create(T object) throws SQLException;

    public abstract T read(I id) throws SQLException;

    public abstract void update(T object) throws SQLException;

    public abstract void delete(T object) throws SQLException;

    public abstract List<T> list(T groupUsers) throws SQLException;

    @Override
    protected void finalize() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
