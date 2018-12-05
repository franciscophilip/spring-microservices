package bcntec.training.springboot.demo1.search.controller;

import lombok.Data;

@Data
public class SearchQuery {
    String origin;
    String destination;
    String flightDate;


    public SearchQuery(String origin, String destination, String flightDate) {
        this.origin = origin;
        this.destination = destination;
        this.flightDate = flightDate;
    }

    @Override
    public String toString() {
        return "SearchQuery [origin=" + origin + ", destination=" + destination + ", flightDate=" + flightDate + "]";
    }


}