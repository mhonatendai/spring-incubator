package entelect.training.incubator.spring.booking.model;

import lombok.Data;

@Data
public class BookingSearchRequest {
    private SearchType searchType;
    private String customerId;
    private String referenceNumber;
}
