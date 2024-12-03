package tequila.ticketbookingplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "events")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private String eventName;
    private String description;
    private String location;
    private LocalDate date;
    private LocalTime time;

//    @ManyToOne
//    @JoinColumn(name = "organizer_id", nullable = false)
//    private User organizer;

}
