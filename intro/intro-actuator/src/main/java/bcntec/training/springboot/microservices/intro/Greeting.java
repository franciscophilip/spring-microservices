package bcntec.training.springboot.microservices.intro;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class Greeting {
    @Id
    @GeneratedValue
    Long id;
    @Column
    Double gauge;

    public Greeting(Double gauge) {
        this.gauge = gauge;
    }
}
