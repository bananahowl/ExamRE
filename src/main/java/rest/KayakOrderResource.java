package rest;

import DTO.BookingDTO;
import DTO.KayakDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Booking;
import entities.Kayak;
import utils.EMF_Creator;
import facades.BookingOrderFacade;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("booking")
public class KayakOrderResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final BookingOrderFacade FACADE =  BookingOrderFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();        
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Kayak Booking endpoint\"}";
    }
    
    @Path("count/kayak")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getKayakCount();
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    @Path("all/kayak")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllKayaks() {
        List<Kayak> kayakList = FACADE.getAllKayaks();
        List<KayakDTO> kayakDTO = new ArrayList();
        for(Kayak elemt : kayakList){
            
        kayakDTO.add(new KayakDTO(elemt));
        }
        return GSON.toJson(kayakDTO);
    }
    
    @Path("all/booking")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllBookings() {
        List<Booking> bookList = FACADE.getAllBookings();
        List<BookingDTO> bookDTO = new ArrayList();
        for(Booking elemt : bookList){
            
        bookDTO.add(new BookingDTO(elemt));
        }
        return GSON.toJson(bookDTO);
    }

 
}
