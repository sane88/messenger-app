package org.vr.messenger.resources;

import org.vr.messenger.model.Message;
import org.vr.messenger.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    private MessageService service = new MessageService();

    @GET
   public List<Message> getMessages(){
       return service.getAllMessages();
   }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId")long id) {
        return service.getMessage(id);
    }

    @POST
    public Message addMessage(Message message){
        return service.addMessage(message);
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId")long id, Message message) {
        message.setId(id);
        return service.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void removeMessage(@PathParam("messageId")long id) {
        service.removeMessage(id);
    }

}
