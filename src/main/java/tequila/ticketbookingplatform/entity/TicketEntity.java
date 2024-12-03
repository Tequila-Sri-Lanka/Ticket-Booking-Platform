package tequila.ticketbookingplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    private float price;
    private String ticketType;
    private String status;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private  event;
}
