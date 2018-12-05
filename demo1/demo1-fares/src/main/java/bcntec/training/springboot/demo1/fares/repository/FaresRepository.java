package bcntec.training.springboot.demo1.fares.repository;

import bcntec.training.springboot.demo1.fares.entity.Fare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaresRepository extends JpaRepository<Fare, Long> {
    Fare getFareByFlightNumberAndFlightDate(String flightNumber, String flightDate);
}
