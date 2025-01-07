package tequila.ticketbookingplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tequila.ticketbookingplatform.entity.TicketBookingEntity;

public interface TicketBookingRepository extends JpaRepository<TicketBookingEntity, Long> {
}
