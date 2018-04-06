package ir.alefmordad.tele.core.manager;

import ir.alefmordad.tele.core.dao.MessageDao;
import ir.alefmordad.tele.core.entities.Message;

public class MessageManager extends Manager<Message, Integer> {

    private static MessageManager instance;

    public static MessageManager getInstance() {
        if (instance == null)
            instance = new MessageManager();
        return instance;
    }

    private MessageManager() {
        setDao(MessageDao.getInstance());
    }

}
