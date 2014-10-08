package dev.bay.rest;

import dev.bay.entity.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/personcontroller")
public class WenController {

    private static Map<Long,Person> personMap = new HashMap<Long, Person>();

    static {
        Person person1 = new Person();
        person1.setId(1);
        person1.setName("Mimimi");
        person1.setSurename("Bahamut");
        personMap.put(person1.getId(),person1);

        Person person2 = new Person();
        person2.setId(2);
        person2.setName("Kokoko");
        person2.setSurename("Barmaglot");
        personMap.put(person2.getId(),person2);
    }

    @GET
    @Path("/hello")
    @Produces("text/plain")
    public String hello() {
        return "Hello to you Big Bro!";
    }

    @GET
    @Path("/message/{message}")
    @Produces("text/plain")
    public String getMessage(@PathParam("message") String message) {
        return message;
    }

    @GET
    @Path("/persons")
    @Produces("application/xml")
    public List<Person> getPersons() {
        return new ArrayList<Person>(personMap.values());
    }

    @GET
    @Path("/person/{personid}")
    @Produces("application/xml")
    public Person getPersonById(@PathParam("personid") String personId) {
        return personMap.get(Long.valueOf(personId));
    }

    @GET
    @Path("/json/persons")
    @Produces("application/json")
    public List<Person> getPersonsJson() {
        return new ArrayList<Person>(personMap.values());
    }

    @GET
    @Path("/json/person/{personid}")
    @Produces("application/json")
    public Person getPersonByIdJson(@PathParam("personid") String personid) {
        return personMap.get(Long.valueOf(personid));
    }

    @POST
    @Path("/createperson")
    @Consumes("application/json")
    public Response createPerson(Person person) {
        personMap.put(person.getId(),person);
        return Response.status(200).entity(person).build();
    }
}
