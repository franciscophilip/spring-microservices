package bcntec.training.springboot.microservices.scalecloud.checkin.repository;

import bcntec.training.springboot.microservices.scalecloud.checkin.entity.CheckInRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinRepository extends JpaRepository<CheckInRecord, Long> {

    default CheckInRecord findOne(Long id) {
        return findById(id).orElse(null);
    }
}
