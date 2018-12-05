package bcntec.training.springboot.microservices.scalecloud.book.component;

import bcntec.training.springboot.microservices.scalecloud.book.entity.BookingRecord;
import bcntec.training.springboot.microservices.scalecloud.book.entity.Inventory;
import bcntec.training.springboot.microservices.scalecloud.book.entity.Passenger;
import bcntec.training.springboot.microservices.scalecloud.book.repository.BookingRepository;
import bcntec.training.springboot.microservices.scalecloud.book.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@EnableFeignClients
@RefreshScope
@Component
public class BookingComponent {

    BookingRepository bookingRepository;
    InventoryRepository inventoryRepository;
    FareServiceProxy fareServiceProxy;


    Sender sender;

    public BookingComponent() {

    }


    @Autowired
    public BookingComponent(BookingRepository bookingRepository,
                            Sender sender, InventoryRepository inventoryRepository, FareServiceProxy fareServiceProxy) {
        this.bookingRepository = bookingRepository;
        this.sender = sender;
        this.inventoryRepository = inventoryRepository;
        this.fareServiceProxy = fareServiceProxy;
    }

    public long book(BookingRecord record) {
        log.info("calling fares to get fare");
        //call fares to get fare
        //	Fare fare = restTemplate.getForObject(fareServiceUrl+FareURL +"/get?flightNumber="+record.getFlightNumber()+"&flightDate="+record.getFlightDate(),Fare.class);
        Fare fare = fareServiceProxy.getFare(record.getFlightNumber(), record.getFlightDate());

        log.info("calling fares to get fare " + fare);
        //check fare
        if (!record.getFare().equals(fare.getFare()))
            throw new BookingException("fare is tampered");
        log.info("calling inventory to get inventory");
        //check inventory
        Inventory inventory = inventoryRepository.findByFlightNumberAndFlightDate(record.getFlightNumber(), record.getFlightDate());
        if (!inventory.isAvailable(record.getPassengers().size())) {
            throw new BookingException("No more seats avaialble");
        }
        log.info("successfully checked inventory" + inventory);
        log.info("calling inventory to update inventory");
        //update inventory
        inventory.setAvailable(inventory.getAvailable() - record.getPassengers().size());
        inventoryRepository.saveAndFlush(inventory);
        log.info("sucessfully updated inventory");
        //save booking
        record.setStatus(BookingStatus.BOOKING_CONFIRMED);
        Set<Passenger> passengers = record.getPassengers();
        passengers.forEach(passenger -> passenger.setBookingRecord(record));
        record.setBookingDate(new Date());
        long id = bookingRepository.save(record).getId();
        log.info("Successfully saved booking");
        //send a message to search to update inventory
        log.info("sending a booking event");
        Map<String, Object> bookingDetails = new HashMap<String, Object>();
        bookingDetails.put("FLIGHT_NUMBER", record.getFlightNumber());
        bookingDetails.put("FLIGHT_DATE", record.getFlightDate());
        bookingDetails.put("NEW_INVENTORY", inventory.getBookableInventory());
        sender.send(bookingDetails);
        log.info("booking event successfully delivered " + bookingDetails);
        return id;
    }

    public BookingRecord getBooking(Long id) {
        return bookingRepository.findOne(id);
    }


    public void updateStatus(String status, long bookingId) {
        BookingRecord record = bookingRepository.findOne(bookingId);
        if (record == null) {
            log.info("NO BOOKING FOUND, ignoring FOR BOOKING ID.." + bookingId);
        } else {
            record.setStatus(status);
        }
    }

}

