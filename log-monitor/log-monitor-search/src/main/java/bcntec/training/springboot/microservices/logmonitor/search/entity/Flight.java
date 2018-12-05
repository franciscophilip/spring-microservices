package bcntec.training.springboot.microservices.logmonitor.search.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String flightNumber;
    private String origin;
    private String destination;
    private String flightDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fare_Id")
    private Fares fares;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inv_Id")
    private Inventory inventory;


    public Flight(String flightNumber, String origin, String destination, String flightDate, Fares fares,
                  Inventory inventory) {
        super();
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.flightDate = flightDate;
        this.fares = fares;
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Flight [id=" + id + ", flightNUmber=" + flightNumber + ", origin=" + origin + ", destination="
                + destination + ", flightDate=" + flightDate + ", fares=" + fares + ", inventory=" + inventory + "]";
    }


}
