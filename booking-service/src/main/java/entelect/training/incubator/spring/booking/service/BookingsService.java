package entelect.training.incubator.spring.booking.service;

import entelect.training.incubator.spring.booking.model.Booking;
import entelect.training.incubator.spring.booking.model.BookingSearchRequest;
import entelect.training.incubator.spring.booking.model.SearchType;
import entelect.training.incubator.spring.booking.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;

@Service
public class BookingsService {

    private final BookingRepository bookingRepository;

    public BookingsService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookings() {
        Iterable<Booking> bookingIterable = bookingRepository.findAll();

        List<Booking> result = new ArrayList<>();
        bookingIterable.forEach(result::add);

        return result;
    }

    public Booking getBookingById(Integer id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        return bookingOptional.orElse(null);
    }

    public Booking getBookingByReferenceNumber(String referenceNumber) {
        Optional<Booking> bookingOptional = bookingRepository.findBookingByReferenceNumber(referenceNumber);
        return bookingOptional.orElse(null);
    }

    public List<Booking> getBookingsByCustomerId(Integer id) {
        return bookingRepository.findAllByCustomerId(id);
    }


    public Booking searchBookingByReferenceNumber(BookingSearchRequest searchRequest) {
        Map<SearchType, Supplier<Optional<Booking>>> searchStrategies = new HashMap<>();

        searchStrategies.put(SearchType.REFERENCE_NUMBER_SEARCH, () -> bookingRepository.findBookingByReferenceNumber(searchRequest.getReferenceNumber()));

        Optional<Booking> bookingOptional = searchStrategies.get(searchRequest.getSearchType()).get();

        return bookingOptional.orElse(null);
    }

    public List<Booking> searchBookingsByCustomerId(BookingSearchRequest searchRequest) {
        Map<SearchType, Supplier<List<Booking>>> searchStrategies = new HashMap<>();

        searchStrategies.put(SearchType.CUSTOMER_ID_SEARCH, () -> bookingRepository.findAllByCustomerId(Integer.valueOf(searchRequest.getCustomerId())));

        List<Booking> bookingList = searchStrategies.get(searchRequest.getSearchType()).get();

        return bookingList;
    }
}
