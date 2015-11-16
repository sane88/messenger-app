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
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
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
    public Message getMessage(@PathParam("messageId")long id, @Context UriInfo uriInfo) {
        Message message = service.getMessage(id);
        message.addLink(getUrlForSelf(uriInfo, message), "self");
        message.addLink(getUrlForProfile(uriInfo, message), "profile");
        message.addLink(getUrlForComments(uriInfo, message), "comments");
        return message;
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

    private String getUrlForSelf(UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(Long.toString(message.getId()))
                .build()
                .toString();
    }

    private String getUrlForProfile(UriInfo uriInfo, Message message){
        return uriInfo.getBaseUriBuilder()
                .path(ProfileResource.class)
                .path(message.getAuthor())
                .build()
                .toString();
    }

    private String getUrlForComments(UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(MessageResource.class, "getCommentResource")
                .resolveTemplate("messageId", message.getId())
                .path(CommentResource.class)
                .build()
                .toString();
    }

}
