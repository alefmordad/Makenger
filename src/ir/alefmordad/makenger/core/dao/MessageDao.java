package ir.alefmordad.makenger.core.dao;

import ir.alefmordad.makenger.core.util.converters.rowmapper.LongRowMapper;
import ir.alefmordad.makenger.core.util.converters.rowmapper.MessageRowMapper;
import ir.alefmordad.makenger.core.util.converters.rowmapper.MessageListRowMapper;
import ir.alefmordad.makenger.core.entities.Message;
import ir.alefmordad.makenger.core.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class MessageDao extends Dao<Message, Long> {

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
        String query = "INSERT INTO messages(id,src_id,dst_id,content,date_,received,seen,deleted,visibleForMe,replyTo,forwardFrom) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, 0);
        ps.setString(2, message.getSource().getId());
        ps.setString(3, message.getDestination().getId());
        ps.setString(4, message.getContent());
        ps.setTimestamp(5, new Timestamp(message.getDate().getTime()));
        ps.setBoolean(6, message.getReceived());
        ps.setBoolean(7, message.getSeen());
        ps.setBoolean(8, message.getDeleted());
        ps.setBoolean(9, message.getVisibleForMe());
        if (message.getReplyTo() == null)
            ps.setLong(10, 0);
        else
            ps.setLong(10, message.getReplyTo().getId());
        if (message.getForwardFrom() == null)
            ps.setString(11, "");
        else
            ps.setString(11, message.getForwardFrom().getId());
        ps.executeUpdate();
        ps.close();
        setIdAfterSave(message);
    }

    @Override
    public Message read(Long id) throws SQLException {
        String query = "SELECT * FROM messages AS mes1 LEFT JOIN messages AS mes2 ON mes1.replyTo=mes2.id WHERE id=? AND deleted=0 AND visibleForMe=0";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Message msg = new MessageRowMapper().convert(rs);
        rs.close();
        ps.close();
        return msg;
    }

    @Override
    public void update(Message message) throws SQLException {
        String query = "UPDATE messages SET content=?, date_=?, received=?, seen=? WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, message.getContent());
        ps.setTimestamp(2, new Timestamp(message.getDate().getTime()));
        ps.setBoolean(3, message.getReceived());
        ps.setBoolean(4, message.getSeen());
        ps.setLong(5, message.getId());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(Message message) throws SQLException {
        String query = "UPDATE messages SET deleted=?, visibleForMe=0 WHERE id=? AND src_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setBoolean(1, message.getDeleted());
        ps.setLong(2, message.getId());
        ps.setString(3, message.getSource().getId());
        ps.executeUpdate();
        ps.close();
    }

    public void setIdAfterSave(Message message) throws SQLException {
        String query = "SELECT id FROM messages WHERE src_id=? AND dst_id=? AND date_=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, message.getSource().getId());
        ps.setString(2, message.getDestination().getId());
        ps.setTimestamp(3, new Timestamp(message.getDate().getTime()));
        ResultSet rs = ps.executeQuery();
        message.setId(new LongRowMapper().convert(rs));
        rs.close();
        ps.close();
    }

    public List<Message> fetch(User user) throws SQLException {
        String query = "SELECT * FROM messages AS mes1 LEFT JOIN messages AS mes2 ON mes1.replyTo=mes2.id WHERE mes1.dst_id=? AND mes1.received=0 AND mes1.deleted=0";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, user.getId());
        ResultSet rs = ps.executeQuery();
        List<Message> messages = new MessageListRowMapper().convert(rs);
        rs.close();
        ps.close();
        return messages;
    }
}
