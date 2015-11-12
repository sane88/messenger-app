package org.vr.messenger.service;


import org.vr.messenger.database.DatabaseMock;
import org.vr.messenger.model.Message;

import javax.xml.crypto.Data;
import java.util.*;

public class MessageService {

    private Map<Long, Message> messages = DatabaseMock.getMessages();

    public MessageService() {
        messages.put(1l, new Message(1l, "Hello World!", "Author"));
        messages.put(2l, new Message(2l, "Hello Jersey!", "Author"));
    }

    public List<Message> getAllMessages(){
        return new ArrayList<>(messages.values());
    }

    public List<Message> getMessagesForYear(int year){
        List<Message> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (Message message : messages.values()) {
            calendar.setTime(message.getCreated());
            if (calendar.get(Calendar.YEAR) == year) {
                result.add(message);
            }
        }
        return result;
    }

    public List<Message> getMessagesPaginated(int start, int size){
        List<Message> result = new ArrayList<>(messages.values());
        return result.subList(start, start + size);
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
