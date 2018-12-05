package bcntec.training.springboot.microservices.config.search.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@NoArgsConstructor
@Setter @Getter
@Entity
public class Fares {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fare_id")
    long id;

    String fare;
    String currency;


    public Fares(String fare, String currency) {
        this.fare = fare;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Fares [id=" + id + ", fare=" + fare + ", currency=" + currency + "]";
    }


}
