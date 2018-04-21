package ir.alefmordad.makenger.core.service;

import ir.alefmordad.makenger.core.entities.Message;
import ir.alefmordad.makenger.core.manager.MessageManager;

public class MessageService extends Service<Message, Long> {

    private static MessageService messageService;

    private MessageService() {setManager(MessageManager.getInstance());}

    public static MessageService getMessageService() {
        if (messageService == null)
            messageService = new MessageService();
        return messageService;
    }

}
