package bcntec.training.springboot.demo1.checkin;

import bcntec.training.springboot.demo1.checkin.entity.CheckInRecord;
import bcntec.training.springboot.demo1.checkin.repository.CheckinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private CheckinRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        CheckInRecord record = new CheckInRecord("Franc", "Gean", "28A", new Date(), "BF101", "22-JAN-18", 1);

        CheckInRecord result = repository.save(record);
        log.info("checked in successfully ..." + result);


        log.info("Looking to load checkedIn record...");
        log.info("Result: " + repository.findById(result.getId()));


    }
}
