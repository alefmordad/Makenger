package ir.alefmordad.makenger.core.manager;

import ir.alefmordad.makenger.core.dao.MessageDao;
import ir.alefmordad.makenger.core.entities.Message;

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
