package facades;

import entities.Booking;
import entities.Image;
import entities.Kayak;
import entities.Role;
import entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class BookingOrderFacade {

    private static BookingOrderFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private BookingOrderFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BookingOrderFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BookingOrderFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getKayakCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long kayatCount = (long) em.createQuery("SELECT COUNT(k) FROM Kayak k").getSingleResult();
            return kayatCount;
        } finally {
            em.close();
        }
    }

    public List<Kayak> getAllKayaks() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("SELECT kay FROM KAYAK kay", Booking.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Booking> getAllBookings() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("SELECT bookings FROM BOOKING bookings", Booking.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void addBooking(User user, Image image, Booking book, Kayak chosenKay) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            chosenKay.getImages().add(image);
            chosenKay.addBooking(book);
            user.addBooking(book);
            em.persist(user);
            em.persist(chosenKay);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void setupCode() {
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

        if (admin.getUserPass().equals("test") || user.getUserPass().equals("test") || both.getUserPass().equals("test")) {
            throw new UnsupportedOperationException("You have not changed the passwords");
        }
        try {

            em.getTransaction().begin();
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

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

}
