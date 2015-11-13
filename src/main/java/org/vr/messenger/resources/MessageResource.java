package org.vr.messenger.resources;

import org.vr.messenger.model.Message;
import org.vr.messenger.resources.beans.MessageFilterBean;
import org.vr.messenger.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    private MessageService service = new MessageService();

    @GET
   public List<Message> getMessages(@BeanParam MessageFilterBean bean){
        if(bean.getYear() > 0) {
            return service.getMessagesForYear(bean.getYear());
        }
        if (bean.getStart() >= 0 && bean.getSize() > 0) {
            return service.getMessagesPaginated(bean.getStart(), bean.getSize());
        }
       return service.getAllMessages();
   }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId")long id) {
        return service.getMessage(id);
    }

    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo){
        Message newMessage = service.addMessage(message);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getId())).build();
        return Response.created(uri)
                .entity(newMessage)
                .build();
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

    @Path("{messageId}/comments")
    public CommentResource getCommentResource(){
        return new CommentResource();
    }

}
