/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author ahmed
 */
@Entity
//@NamedQuery(name = "Kayak.deleteAllRows", query = "DELETE from Kayak")
public class Kayak implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    private String model;
    
    private String desciption;
    
    private int year;
    
    private String color;
    
    private int personsAllowed;
    
    @OneToMany(mappedBy = "kayakIDB",cascade = CascadeType.PERSIST)
    private List<Booking> bookingList = new ArrayList();
    
    @OneToMany(mappedBy = "kayakID",cascade = CascadeType.PERSIST)
    private List<Image> images = new ArrayList();

    public Kayak() {
    }
    
    public Kayak(String name, String model, String desciption, int year, String color, int personsAllowed) {
        this.name = name;
        this.model = model;
        this.desciption = desciption;
        this.year = year;
        this.color = color;
        this.personsAllowed = personsAllowed;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPersonsAllowed() {
        return personsAllowed;
    }

    public void setPersonsAllowed(int personsAllowed) {
        this.personsAllowed = personsAllowed;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> BookingList) {
        this.bookingList = BookingList;
    }
    
    public void addBooking(Booking book){
        bookingList.add(book);
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kayak)) {
            return false;
        }
        Kayak other = (Kayak) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }


    
}
