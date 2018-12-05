package bcntec.training.springboot.microservices.docker.pss.search.component;

import bcntec.training.springboot.microservices.docker.pss.search.controller.SearchQuery;
import bcntec.training.springboot.microservices.docker.pss.search.entity.Flight;
import bcntec.training.springboot.microservices.docker.pss.search.entity.Inventory;
import bcntec.training.springboot.microservices.docker.pss.search.repository.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SearchComponent {
    private FlightRepository flightRepository;


    @Autowired
    public SearchComponent(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> search(SearchQuery query) {
        List<Flight> flights = flightRepository.findByOriginAndDestinationAndFlightDate(
                query.getOrigin(),
                query.getDestination(),
                query.getFlightDate());
        List<Flight> searchResult = new ArrayList<Flight>();
        searchResult.addAll(flights);
        flights.forEach(flight -> {
            flight.getFares();
            int inv = flight.getInventory().getCount();
            if (inv < 0) {
                searchResult.remove(flight);
            }
        });
        return searchResult;
    }

    public void updateInventory(String flightNumber, String flightDate, int inventory) {
        log.info("Updating inventory for flight " + flightNumber + " innventory " + inventory);
        Flight flight = flightRepository.findByFlightNumberAndFlightDate(flightNumber, flightDate);
        Inventory inv = flight.getInventory();
        inv.setCount(inventory);
        flightRepository.save(flight);
    }
}
