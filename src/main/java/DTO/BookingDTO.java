/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Booking;
import java.util.Date;

/**
 *
 * @author ahmed
 */
public class BookingDTO {
    
    private Date bookingCreated;
    private String bookingName;

    public BookingDTO(Booking booking) {
        this.bookingCreated = booking.getBookingDate();
        this.bookingName = booking.getBookingName();
    }

    public Date getBookingCreated() {
        return bookingCreated;
    }

    public void setBookingCreated(Date bookingCreated) {
        this.bookingCreated = bookingCreated;
    }

    public String getBookingName() {
        return bookingName;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    @Override
    public String toString() {
        return "BookingDTO{" + "bookingCreated=" + bookingCreated + ", bookingName=" + bookingName + '}';
    }
    
    
    
}
