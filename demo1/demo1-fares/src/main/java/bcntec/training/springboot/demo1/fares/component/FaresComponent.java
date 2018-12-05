package bcntec.training.springboot.demo1.fares.component;

import bcntec.training.springboot.demo1.fares.entity.Fare;
import bcntec.training.springboot.demo1.fares.repository.FaresRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FaresComponent {

    private final FaresRepository faresRepository;

    @Autowired
    public FaresComponent(FaresRepository faresRepository) {
        this.faresRepository = faresRepository;
    }

    public Fare getFare(String flightNumber, String flightDate) {
        log.info("Looking for fares flightNumber " + flightNumber + " flightDate " + flightDate);
        return faresRepository.getFareByFlightNumberAndFlightDate(flightNumber, flightDate);
    }
}
