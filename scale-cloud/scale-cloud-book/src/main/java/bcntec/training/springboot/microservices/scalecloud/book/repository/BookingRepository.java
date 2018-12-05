package bcntec.training.springboot.microservices.scalecloud.book.repository;


import bcntec.training.springboot.microservices.scalecloud.book.entity.BookingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingRecord, Long> {

    default BookingRecord findOne(Long id) {
        return findById(id).orElse(null);
    }
}
