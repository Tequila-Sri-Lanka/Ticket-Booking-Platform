package tequila.ticketbookingplatform.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tequila.ticketbookingplatform.entity.UserEntity;

import java.util.List;

public interface UserRepo  extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByUserName(String userName);
}