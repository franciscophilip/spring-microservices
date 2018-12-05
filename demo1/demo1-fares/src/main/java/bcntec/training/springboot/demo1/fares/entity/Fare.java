package bcntec.training.springboot.demo1.fares.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Fare {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String flightNumber;
    private String flightDate;
    private String fare;


    public Fare(String flightNumber, String flightDate, String fare) {
        this.flightNumber = flightNumber;
        this.flightDate = flightDate;
        this.fare = fare;
    }


    @Override
    public String toString() {
        return "Fares [id=" + id + ", flightNumber=" + flightNumber + ", flightDate=" + flightDate + ", fare=" + fare
                + "]";
    }


}
