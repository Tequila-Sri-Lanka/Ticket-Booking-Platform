package tequila.ticketbookingplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketBookingDTO {
    private long id;
    private Long userId;
    private Long ticketId;
    private int quantity;
    private double totalPrice;
    private LocalDateTime bookingDate;
}
