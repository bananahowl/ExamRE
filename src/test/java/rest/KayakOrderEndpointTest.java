package rest;

import entities.Booking;
import entities.Image;
import entities.Kayak;
import entities.Role;
import entities.User;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

//@Disabled
public class KayakOrderEndpointTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.CREATE);

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            em.createQuery("delete from User").executeUpdate();
            em.createQuery("delete from Role").executeUpdate();
            em.createQuery("delete from Kayak").executeUpdate();
            em.createQuery("delete from Image").executeUpdate();
            em.createQuery("delete from Booking").executeUpdate();
            User user = new User("hans", "1235");
            User user2 = new User("mads", "1235");
            User admin = new User("admin", "1235");
            User both = new User("user_admin", "1235");

            Image image1 = new Image("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Farawakexp.com%2Fwp-content%2Fuploads%2F2012%2F10%2Frental-yaks.jpg&f=1&nofb=1 ");
            Image image2 = new Image("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.kayakhire.com.au%2Fwp-content%2Fuploads%2F2016%2F08%2FP1070216-1024x768.jpg&f=1&nofb=1");
            Booking booking1 = new Booking("hansens order");
            Booking booking2 = new Booking("madsens order");
            Kayak kayak1 = new Kayak("kayak1", "CLD21", "a kayak to be used by 2 persons", 2, "yellow", 2);
            Kayak kayak2 = new Kayak("kayak2", "CXLD22", "a kayak to be used by 1 persons", 1, "yellow", 1);
            Kayak kayak3 = new Kayak("kayak3", "CLD221", "a kayak to be used by 3 persons", 1, "blue", 1);

            Role userRole = new Role("user");
            Role adminRole = new Role("admin");
            user.addRole(userRole);
            admin.addRole(adminRole);
            both.addRole(userRole);
            both.addRole(adminRole);

            kayak1.getImages().add(image1);
            kayak2.getImages().add(image2);
            kayak3.getImages().add(image2);
            // Can see that the realtion between booking and kayat would maybe be better reverse here 
            kayak1.addBooking(booking1);
            kayak2.addBooking(booking1);
            user.addBooking(booking1);

            kayak3.addBooking(booking2);
            user2.addBooking(booking2);

            em.persist(userRole);
            em.persist(adminRole);
            em.persist(user);
            em.persist(user2);
            em.persist(admin);
            em.persist(both);

            em.persist(image1);
            em.persist(image2);
            em.persist(booking1);
            em.persist(kayak1);
            em.persist(kayak2);
            em.persist(kayak3);

            System.out.println("Saved test data to database");
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void serverIsRunning() {
        System.out.println("Testing is server UP");
        given().when().get("/booking").then().statusCode(200);
    }

    @Test
    public void testRestNoAuthenticationRequired() {
        given()
                .contentType("application/json")
                .when()
                .get("/booking").then()
                .statusCode(200)
                .body("msg", equalTo("Kayak Booking endpoint"));
    }

    @Test // get status error 500, not time find mistake later
    public void amountOfKayaksTest() {
        given()
                .contentType("application/json")
                .when()
                .get("/booking/all/kayak").then()
                .statusCode(200)
                .body(equalTo("[3]"));
    }

}
