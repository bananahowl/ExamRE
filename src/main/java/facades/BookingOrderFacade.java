package facades;

import entities.Booking;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class BookingOrderFacade {

    private static BookingOrderFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private BookingOrderFacade() {}
    
    
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
    public long getKayakCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long kayatCount = (long)em.createQuery("SELECT COUNT(r) FROM KAYAK r").getSingleResult();
            return kayatCount;
        }finally{  
            em.close();
        }
        
    }
    
//    public Booking addBooking(){
//    EntityManager em = emf.createEntityManager();
//    
//    try{
//    em.getTransaction().begin();
//        
//    }finally{
//    em.close();
//    }
//    
//    
//    }

    
}
