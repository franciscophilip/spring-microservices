package bcntec.training.springboot.microservices.scalecloud.fares.component;

import bcntec.training.springboot.microservices.scalecloud.fares.entity.Fare;
import bcntec.training.springboot.microservices.scalecloud.fares.repository.FaresRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FaresComponent {

    FaresRepository faresRepository;

    public FaresComponent() {

    }

    @Autowired
    public FaresComponent(FaresRepository faresRepository) {
        this.faresRepository = faresRepository;
    }

    public Fare getFare(String flightNumber, String flightDate) {
        log.info("Looking for fares flightNumber " + flightNumber + " flightDate " + flightDate);
        return faresRepository.getFareByFlightNumberAndFlightDate(flightNumber, flightDate);
    }
}
