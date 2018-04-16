package ir.alefmordad.makenger.core.manager;

import ir.alefmordad.makenger.core.dao.MessageDao;
import ir.alefmordad.makenger.core.entities.Message;
import ir.alefmordad.makenger.core.entities.User;

import java.sql.SQLException;
import java.util.List;

public class MessageManager extends Manager<Message, Long> {

    private static MessageManager instance;

    public static MessageManager getInstance() {
        if (instance == null)
            instance = new MessageManager();
        return instance;
    }

    private MessageManager() {
        setDao(MessageDao.getInstance());
    }

    public List<Message> fetch(User user) throws SQLException {
        return ((MessageDao) dao).fetch(user);
    }

}
