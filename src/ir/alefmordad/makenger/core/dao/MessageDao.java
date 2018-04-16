package ir.alefmordad.makenger.core.dao;

import ir.alefmordad.makenger.core.util.converters.rowmapper.IntegerRowMapper;
import ir.alefmordad.makenger.core.util.converters.rowmapper.MessageRowMapper;
import ir.alefmordad.makenger.core.util.converters.rowmapper.MessageListRowMapper;
import ir.alefmordad.makenger.core.entities.Message;
import ir.alefmordad.makenger.core.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class MessageDao extends Dao<Message, Integer> {

    public static MessageDao getInstance() {
        if (instance == null)
            instance = new MessageDao();
        return instance;
    }

    private static MessageDao instance;

    private MessageDao() {
    }

    @Override
    public void create(Message message) throws SQLException {
        String query = "insert into messages(id,src_id,dst_id,content,date_,received,seen) values(?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, 0);
        ps.setString(2, message.getSource().getId());
        ps.setString(3, message.getDestination().getId());
        ps.setString(4, message.getContent());
        ps.setTimestamp(5, new Timestamp(message.getDate().getTime()));
        ps.setBoolean(6, message.getReceived());
        ps.setBoolean(7, message.getSeen());
        ps.executeUpdate();
        ps.close();
        setIdAfterSave(message);
    }

    @Override
    public Message read(Integer id) throws SQLException {
        String query = "select * from messages where id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Message msg = new MessageRowMapper().convert(rs);
        rs.close();
        ps.close();
        return msg;
    }

    @Override
    public void update(Message message) throws SQLException {
        String query = "update messages set src_id=?, dst_id=?, content=?, date_=?, received=?, seen=? where id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, message.getSource().getId());
        ps.setString(2, message.getDestination().getId());
        ps.setString(3, message.getContent());
        ps.setTimestamp(4, new Timestamp(message.getDate().getTime()));
        ps.setBoolean(5, message.getReceived());
        ps.setBoolean(6, message.getSeen());
        ps.setInt(7, message.getId());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String query = "delete from messages where id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }

    public void setIdAfterSave(Message message) throws SQLException {
        String query = "select id from messages where src_id=? and dst_id=? and date_=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, message.getSource().getId());
        ps.setString(2, message.getDestination().getId());
        ps.setTimestamp(3, new Timestamp(message.getDate().getTime()));
        ResultSet rs = ps.executeQuery();
        message.setId(new IntegerRowMapper().convert(rs));
        rs.close();
        ps.close();
    }

    public List<Message> fetch(User user) throws SQLException {
        String query = "select * from messages where dst_id=? and received=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, user.getId());
        ps.setBoolean(2, false);
        ResultSet rs = ps.executeQuery();
        List<Message> messages = new MessageListRowMapper().convert(rs);
        rs.close();
        ps.close();
        return messages;
    }
}
