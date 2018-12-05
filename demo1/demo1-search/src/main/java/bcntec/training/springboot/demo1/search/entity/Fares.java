package bcntec.training.springboot.demo1.search.entity;

import javax.persistence.*;

@Entity
public class Fares {

    String fare;
    String currency;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fare_id")
    private Long id;


    public Fares(String fare, String currency) {
        super();
        this.fare = fare;
        this.currency = currency;
    }
}
