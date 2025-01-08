package tequila.ticketbookingplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDTO {
    private Long ticketId;

    private float price;
    private String ticketType;
    private String status;

    private String eventId;

    private  int qty;
}
