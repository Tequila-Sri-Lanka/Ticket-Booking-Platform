package tequila.ticketbookingplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tequila.ticketbookingplatform.entity.EventEntity;
@Repository
public interface EventRepo extends JpaRepository<EventEntity, Long> {

}

