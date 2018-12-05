package bcntec.training.springboot.microservices.scalecloud.fares.repository;

import bcntec.training.springboot.microservices.scalecloud.fares.entity.Fare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaresRepository extends JpaRepository<Fare, Long> {
    Fare getFareByFlightNumberAndFlightDate(String flightNumber, String flightDate);
}
