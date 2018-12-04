package bcntec.training.springboot.demo1.checkin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bcntec.training.springboot.demo1.checkin.entity.CheckInRecord;

public interface CheckinRepository extends JpaRepository<CheckInRecord,Long> {

}
