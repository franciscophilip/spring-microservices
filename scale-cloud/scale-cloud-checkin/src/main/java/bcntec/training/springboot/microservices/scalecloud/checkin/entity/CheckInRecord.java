package bcntec.training.springboot.microservices.scalecloud.checkin.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class CheckInRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lastName;
    private String firstName;
    private String seatNumber;
    private Date checkInTime;
    private String flightNumber;
    private String flightDate;
    private Long bookingId;

    public CheckInRecord(String lastName, String firstName, String seatNumber, Date checkInTime, String flightNumber,
                         String flightDate, long bookingId) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.seatNumber = seatNumber;
        this.checkInTime = checkInTime;
        this.flightNumber = flightNumber;
        this.flightDate = flightDate;
        this.bookingId = bookingId;
    }


    @Override
    public String toString() {
        return "CheckInRecord [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", seatNumber="
                + seatNumber + ", checkInTime=" + checkInTime + ", flightNumber=" + flightNumber + ", flightDate="
                + flightDate + "]";
    }


}
