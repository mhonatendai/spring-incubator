package entelect.training.incubator.spring.booking.repository;

import entelect.training.incubator.spring.booking.model.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends CrudRepository<Booking,Integer> {

    Optional<Booking> findBookingByReferenceNumber(String referenceNumber);

    List<Booking> findAllByCustomerId(Integer customerId);

}
