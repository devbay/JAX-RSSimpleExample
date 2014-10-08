package dev.bay.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/hello")
public class SimpleRest {

    @GET
    @Path("/{param}")
    public Response getMessage(@PathParam("param") String param) {
        String outPut = param + " take a rest!";
        return Response.status(200).entity(outPut).build();
    }
}
