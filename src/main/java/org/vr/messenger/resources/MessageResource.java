package org.vr.messenger.resources;

import org.vr.messenger.model.Message;
import org.vr.messenger.service.MessageService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/messages")
public class MessageResource {

    private MessageService service = new MessageService();

    @GET
    @Produces(MediaType.APPLICATION_XML)
   public List<Message> getMessages(){
       return service.getAllMessages();
   }

}
