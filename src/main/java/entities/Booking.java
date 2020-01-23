package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@NamedQuery(name = "Booking.deleteAllRows", query = "DELETE from Booking")
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date bookingDate;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User userID;
   
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Kayak kayakIDB;
    
    private String bookingName;
    
    public Booking() {
    }

    public Booking(String bookName) {
        this.bookingDate = new Date();
        this.bookingName = bookName;
    }

     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }    

    public User getUserID() {
        return userID;
    }

    public Kayak getKayakIDB() {
        return kayakIDB;
    }
    
    public String getBookingName() {
        return bookingName;
    }
    
    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }    

    @Override
    public String toString() {
        return "Booking{" + "id=" + id + ", bookingDate=" + bookingDate + ", userID=" + userID + ", kayakIDB=" + kayakIDB + ", bookingName=" + bookingName + '}';
    }

   
}
