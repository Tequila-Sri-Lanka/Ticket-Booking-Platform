package tequila.ticketbookingplatform.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tequila.ticketbookingplatform.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepo  extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByUserName(String userName);
    Optional<UserEntity> findByEmail(String email);
}