package ir.alefmordad.makenger.core.converters;

import ir.alefmordad.makenger.core.entities.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetToMessageListConverter implements Converter<ResultSet, List<Message>> {

    @Override
    public List<Message> convert(ResultSet from) throws SQLException {
        List<Message> messages = new ArrayList<>();
        while (from.next()) {
            from.previous();
            messages.add(new ResultSetToMessageConverter().convert(from));
        }
        return messages;
    }

}
