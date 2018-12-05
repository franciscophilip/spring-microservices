package bcntec.training.springboot.demo1.search.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "inv_id")
    private Long id;

    private Integer count;

    public Inventory(Integer count) {
        this.count = count;
    }
}
