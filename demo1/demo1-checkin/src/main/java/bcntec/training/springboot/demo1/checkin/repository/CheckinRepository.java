package bcntec.training.springboot.demo1.checkin.repository;

import bcntec.training.springboot.demo1.checkin.entity.CheckInRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinRepository extends JpaRepository<CheckInRecord, Long> {

}
