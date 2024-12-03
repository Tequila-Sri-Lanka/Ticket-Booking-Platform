package tequila.ticketbookingplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDTO {
    private Long eventId;
    private String eventName;
    private String description;
    private String location;
    private LocalDate date;
    private LocalTime time;
}
