package ir.alefmordad.tele.core.dao;

import ir.alefmordad.tele.core.converters.ResultSetToMessageConverter;
import ir.alefmordad.tele.core.entities.Message;

import java.sql.SQLException;
import java.sql.Timestamp;

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
    public Message create(Message message) throws SQLException {
        String query = "insert into messages(id,src_id,dst_id,content,date_,received) values(?,?,?,?,?,?)";
        ps = connection.prepareStatement(query);
        ps.setInt(1, 0);
        ps.setString(2, message.getSource().getId());
        ps.setString(3, message.getDestination().getId());
        ps.setString(4, message.getContent());
        ps.setTimestamp(5, new Timestamp(message.getDate().getTime()));
        ps.setBoolean(6, message.getReceived());
        ps.executeUpdate();
        return null;
    }

    @Override
    public Message read(Integer id) throws SQLException {
        String query = "select * from messages where id=?";
        ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        Message msg = new ResultSetToMessageConverter().convert(rs);
        rs.close();
        return msg;
    }

    @Override
    public void update(Message message) throws SQLException {
        String query = "update messages set src_id=?, dst_id=?, content=?, date_=?, received=? where id=?";
        ps = connection.prepareStatement(query);
        ps.setString(1, message.getSource().getId());
        ps.setString(2, message.getDestination().getId());
        ps.setString(3, message.getContent());
        ps.setTimestamp(4, new Timestamp(message.getDate().getTime()));
        ps.setBoolean(5, message.getReceived());
        ps.setInt(6, message.getId());
        ps.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String query = "delete form messages where id=?";
        ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

}
