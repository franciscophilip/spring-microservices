package bcntec.training.springboot.demo1.checkin.component;

import bcntec.training.springboot.demo1.checkin.entity.CheckInRecord;
import bcntec.training.springboot.demo1.checkin.repository.CheckinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class CheckinComponent {

    private final CheckinRepository checkinRepository;
    private final Sender sender;

    @Autowired
    CheckinComponent(CheckinRepository checkinRepository, Sender sender) {
        this.checkinRepository = checkinRepository;
        this.sender = sender;
    }

    public long checkIn(CheckInRecord checkIn) {
        checkIn.setCheckInTime(new Date());
        log.info("Saving checkin ");
        //save
        long id = checkinRepository.save(checkIn).getId();
        log.info("Successfully saved checkin ");
        //send a message back to booking to update status
        log.info("Sending booking id " + id);
        sender.send(id);
        return id;

    }

    public CheckInRecord getCheckInRecord(Long id) {
        return checkinRepository.findById(id).get();
    }

}	
