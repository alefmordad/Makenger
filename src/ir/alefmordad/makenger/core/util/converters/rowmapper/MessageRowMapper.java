package ir.alefmordad.makenger.core.util.converters.rowmapper;

import ir.alefmordad.makenger.core.entities.Message;
import ir.alefmordad.makenger.core.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageRowMapper implements RowMapper<Message> {

    @Override
    public Message convert(ResultSet from) throws SQLException {
        Message msg = new Message();
        if (!from.next())
            return null;
        msg.setId(from.getLong(1));
        msg.setSource(new User(from.getString(2)));
        msg.setDestination(new User(from.getString(3)));
        msg.setContent(from.getString(4));
        msg.setDate(from.getTimestamp(5));
        msg.setReceived(from.getBoolean(6));
        msg.setSeen(from.getBoolean(7));
        if (from.getLong(10) != 0) {
            Message replyTo = new Message();
            replyTo.setId(from.getLong(12));
            replyTo.setSource(new User(from.getString(13)));
            replyTo.setContent(from.getString(15));
            msg.setReplyTo(replyTo);
        }
        msg.setForwardFrom(new User(from.getString(11)));
        return msg;
    }

}
