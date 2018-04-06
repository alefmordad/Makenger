package ir.alefmordad.tele.core.converters;

import ir.alefmordad.tele.core.entities.Message;
import ir.alefmordad.tele.core.entities.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToMessageConverter implements Converter<ResultSet, Message> {

    @Override
    public Message convert(ResultSet from) throws SQLException {
        Message msg = new Message();
        from.next();
        msg.setId(from.getInt(1));
        msg.setSource(new User(from.getString(2)));
        msg.setDestination(new User(from.getString(3)));
        msg.setContent(from.getString(4));
        msg.setDate(from.getDate(5));
        return msg;
    }

}
