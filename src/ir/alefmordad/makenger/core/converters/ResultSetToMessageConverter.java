package ir.alefmordad.makenger.core.converters;

import ir.alefmordad.makenger.core.entities.Message;
import ir.alefmordad.makenger.core.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToMessageConverter implements Converter<ResultSet, Message> {

    @Override
    public Message convert(ResultSet from) throws SQLException {
        Message msg = new Message();
        if (!from.next())
            return null;
        msg.setId(from.getInt(1));
        msg.setSource(new User(from.getString(2)));
        msg.setDestination(new User(from.getString(3)));
        msg.setContent(from.getString(4));
        msg.setDate(from.getDate(5));
        msg.setReceived(from.getBoolean(6));
        msg.setSeen(from.getBoolean(7));
        return msg;
    }

}
