package  bcntec.training.springboot.microservices.config.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

    RestTemplate searchClient = new RestTemplate();

    RestTemplate bookingClient = new RestTemplate();

    RestTemplate checkInClient = new RestTemplate();

    //RestTemplate restClient= new RestTemplate();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        //Search for a flight
        Flight[] flights = null;
        SearchQuery searchQuery = new SearchQuery("NYC", "SFO", "22-JAN-18");
        try {
            flights = searchClient.postForObject("http://35.163.60.130:8090/search/get", searchQuery, Flight[].class);

            Arrays.asList(flights).forEach(flight -> log.info(" flight >" + flight));

        } catch (Exception e) {
            log.info("Flight Service not available");
            return;
        }
        //create a booking only if there are flights.
        if (flights == null || flights.length == 0) {
            return;
        }
        Flight flight = flights[0];
        BookingRecord booking = new BookingRecord(flight.getFlightNumber(), flight.getOrigin(),
                flight.getDestination(), flight.getFlightDate(), null,
                flight.getFares().getFare());
        Set<Passenger> passengers = new HashSet<Passenger>();
        passengers.add(new Passenger("Gavin", "Franc", "Male", booking));
        booking.setPassengers(passengers);
        long bookingId = 0;
        try {
            //long bookingId = bookingClient.postForObject("http://book-service/booking/create", booking, long.class);
            bookingId = bookingClient.postForObject("http://35.163.60.130:8060/booking/create", booking, long.class);
            log.info("Booking created " + bookingId);
        } catch (Exception e) {
            log.error("BOOKING SERVICE NOT AVAILABLE...!!!");
        }

        //check in passenger
        if (bookingId == 0) return;
        try {
            CheckInRecord checkIn = new CheckInRecord("Franc", "Gavin", "28C", null, "BF101", "22-JAN-18", bookingId);
            long checkinId = checkInClient.postForObject("http://35.163.60.130:8070/checkin/create", checkIn, long.class);
            log.info("Checked IN " + checkinId);
        } catch (Exception e) {
            log.error("CHECK IN SERVICE NOT AVAILABLE...!!!");
        }
    }

}