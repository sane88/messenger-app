package org.vr.messenger.resources;

import org.vr.messenger.model.Profile;
import org.vr.messenger.service.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {
    private ProfileService service = new ProfileService();

    @GET
    public List<Profile> getAllProfiles() {
        return service.getAllProfiles();
    }

    @GET
    @Path("/{name}")
    public Profile getProfile(@PathParam("name")String name) {
        return service.getProfile(name);
    }

    @POST
    public Profile addProfile(Profile profile) {
        return service.addProfile(profile);
    }

    @PUT
    @Path("/{name}")
    public Profile updateProfile(@PathParam("name") String name, Profile profile) {
        profile.setProfileName(name);
        return service.updateProfile(profile);
    }

    @DELETE
    @Path("/{name}")
    public void removeProfile(@PathParam("name") String name) {
        service.removeProfile(name);
    }
}
