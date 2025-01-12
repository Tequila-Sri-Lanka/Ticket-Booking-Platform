package tequila.ticketbookingplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tequila.ticketbookingplatform.entity.SportEntity;

public interface SportRepo extends JpaRepository<SportEntity,Long> {
}
