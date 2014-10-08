package dev.bay.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import dev.bay.entity.Person;

public class RestClient {

    public static void main(String[] args) {
        RestClient client = new RestClient();
        System.out.println(client.hello());
        client.getMessage("Message!");
        System.out.println(client.getPersons());
        System.out.println(client.getPersonsJson());

        Person person = new Person();
        person.setId(3);
        person.setName("Hehehe");
        person.setSurename("Meriland");

        client.createPerson(person);

        Person person12= new Person();
        person.setId(5);
        person.setName("Ololo11");
        person.setSurename("Kentuki11");

        client.createPerson(person12);

        System.out.println(client.getPersonsJson());
    }

    private void getMessage(String message) {
        Client client = Client.create();
        WebResource webResource = client.
                resource("http://localhost:8080/devbay_jax_rs_simpleexample_war_exploded/rest/personcontroller/message/" + message);
        ClientResponse response = webResource.accept("text/plain").get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException(String.valueOf(response.getStatus()));
        }

        String output = response.getEntity(String.class);
        System.out.println("\n============getResponse============");
        System.out.println(output);
    }

    private String hello() {
        Client client = Client.create();
        WebResource webResource = client.
                resource("http://localhost:8080/devbay_jax_rs_simpleexample_war_exploded/rest/personcontroller/hello");
        ClientResponse response = webResource.accept("text/plain").get(ClientResponse.class);
        String output = response.getEntity(String.class);
        return output;
    }

    private String getPersons() {
        Client client = Client.create();
        WebResource webResource = client.
                resource("http://localhost:8080/devbay_jax_rs_simpleexample_war_exploded/rest/personcontroller/persons");
        ClientResponse response = webResource.accept("application/xml").get(ClientResponse.class);
        String output = response.getEntity(String.class);
        return output;
    }

    private String getPersonsJson() {
        Client client = Client.create();
        WebResource webResource = client.
                resource("http://localhost:8080/devbay_jax_rs_simpleexample_war_exploded/rest/personcontroller/json/persons");
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        String output = response.getEntity(String.class);
        return output;
    }

    private void createPerson(Person person) {


        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(clientConfig);

        WebResource webResource = client.
                resource("http://localhost:8080/devbay_jax_rs_simpleexample_war_exploded/rest/personcontroller/createperson");
        ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class,person);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
        }

        String output = response.getEntity(String.class);
        System.out.println(output);

    }
}
