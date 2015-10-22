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

    public MessageService() {
        messages.put(1l, new Message(1l, "Hello World!", "Author"));
        messages.put(2l, new Message(2l, "Hello Jersey!", "Author"));
    }

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
