package facades;

import entities.Booking;
import entities.Image;
import entities.Kayak;
import entities.Role;
import entities.User;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//status on test. a bit set up
//@Disabled
public class FacadeTest {

    private static EntityManagerFactory emf;
    private static BookingOrderFacade facade;

    public FacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = BookingOrderFacade.getFacadeExample(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = BookingOrderFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
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
        try {
            em.getTransaction().begin();
//            em.createNamedQuery("ussers.deleteAllRows").executeUpdate();
//            em.createNamedQuery("roles.deleteAllRows").executeUpdate();
//            em.createNamedQuery("Image.deleteAllRows").executeUpdate();
//            em.createNamedQuery("Kayak.deleteAllRows").executeUpdate();
//            em.createNamedQuery("Booking.deleteAllRows").executeUpdate();
            
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
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

//    @Test
//    public void testAFacadeMethod() {
//        assertEquals(2, facade.getKayakCount(), "Expects two rows in the database");
//    }
    
    @Test
    public void amountKayaksTest(){
        assertEquals(3, facade.getKayakCount());
    }
}
