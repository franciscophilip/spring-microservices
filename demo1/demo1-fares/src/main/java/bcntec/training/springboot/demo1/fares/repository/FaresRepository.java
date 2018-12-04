package bcntec.training.springboot.demo1.fares.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bcntec.training.springboot.demo1.fares.entity.Fare;

public interface FaresRepository extends JpaRepository<Fare,Long> {
	Fare getFareByFlightNumberAndFlightDate(String flightNumber, String flightDate);
}
