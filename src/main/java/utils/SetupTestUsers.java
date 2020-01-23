package utils;


import entities.Booking;
import entities.Image;
import entities.Kayak;
import entities.Role;
import entities.User;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    EntityManager em = emf.createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User user = new User("user", "1235");
    User admin = new User("admin", "1235");
    User both = new User("user_admin", "1235");
    
    Image image1 = new Image("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Farawakexp.com%2Fwp-content%2Fuploads%2F2012%2F10%2Frental-yaks.jpg&f=1&nofb=1 ");
    Image image2 = new Image("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.kayakhire.com.au%2Fwp-content%2Fuploads%2F2016%2F08%2FP1070216-1024x768.jpg&f=1&nofb=1");
    Booking booking1 = new Booking("hansens order");
    Kayak kayak1 = new Kayak("kayak1", "CLD21", "a kayak to be used of 2 persons", 2, "yellow", 2);
    Kayak kayak2 = new Kayak("kayak1", "CLD21", "a kayak to be used of 1 persons", 1, "yellow", 1);
    
    
    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    kayak1.getImages().add(image1);
    kayak2.getImages().add(image2); 
            
    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    user.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);
    em.persist(userRole);
    em.persist(adminRole);
    em.persist(user);
    em.persist(admin);
    em.persist(both);
    em.persist(image1);
    em.persist(image2);
    em.persist(booking1);
    em.persist(kayak1);
    em.persist(kayak2);
    
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");
   
  }

}
