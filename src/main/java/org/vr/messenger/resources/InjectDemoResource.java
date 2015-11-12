package org.vr.messenger.resources;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

    @GET
    @Path("annotations")
    public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
                                            @HeaderParam("customHeader") String header,
                                            @CookieParam("name") String cookie) {

        return "MatrixParam is: " + matrixParam + "\n Header is: " + header + "\n Cookie is: " + cookie;
    }

    @GET
    @Path("context")
    public String getParamsFromContext(@Context UriInfo uriInfo, @Context HttpHeaders headers){
        return "Path: " + uriInfo.getAbsolutePath() + "\n Cookies: " + headers.getCookies().toString();
    }
}
