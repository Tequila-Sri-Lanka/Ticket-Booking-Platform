package tequila.ticketbookingplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tequila.ticketbookingplatform.entity.MovieEntity;

public interface MovieRepo extends JpaRepository<MovieEntity,Long> {
}
