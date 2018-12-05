package bcntec.training.springboot.microservices.scalecloud.checkin.component;

import bcntec.training.springboot.microservices.scalecloud.checkin.entity.CheckInRecord;
import bcntec.training.springboot.microservices.scalecloud.checkin.repository.CheckinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class CheckinComponent {

    CheckinRepository checkinRepository;
    Sender sender;

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

    public CheckInRecord getCheckInRecord(long id) {
        return checkinRepository.findOne(id);
    }

}	
