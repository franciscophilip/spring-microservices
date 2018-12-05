package bcntec.training.springboot.microservices.scalecloud.book.repository;


import bcntec.training.springboot.microservices.scalecloud.book.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findByFlightNumberAndFlightDate(String flightNumber, String flightDate);

}
