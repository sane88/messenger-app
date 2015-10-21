package org.vr.messenger.service;


import org.vr.messenger.database.DatabaseMock;
import org.vr.messenger.model.Message;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MessageService {

    private Map<Long, Message> messages = DatabaseMock.getMessages();

    public List<Message> getAllMessages(){
        return new ArrayList<>(messages.values());
    }

    public Message getMessage(long id){
        return messages.get(id);
    }

    public Message addMessage(Message message){
        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message) {
        if (message.getId() <= 0) {
            return null;
        }
        messages.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(long id) {
        return messages.remove(id);
    }
}
