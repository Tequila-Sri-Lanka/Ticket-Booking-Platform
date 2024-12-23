package tequila.ticketbookingplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tequila.ticketbookingplatform.entity.TicketEntity;

import java.util.Optional;
@Repository
public interface TicketRepo extends JpaRepository<TicketEntity,Long> {

    Optional<TicketEntity> findById(Long id);
}
