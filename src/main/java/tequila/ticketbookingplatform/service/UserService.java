package tequila.ticketbookingplatform.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import tequila.ticketbookingplatform.dto.UserDto;

import java.util.List;

public interface UserService {
    public void saveUser(UserDto userDTO);
    public void deleteUser(Long id);
    public void updateUser(Long id, UserDto updatedUser);
    public List<UserDto> getAllUsers();
    UserDetailsService userDetailsService();
}
