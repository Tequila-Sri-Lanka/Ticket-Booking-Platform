package tequila.ticketbookingplatform.service;

import tequila.ticketbookingplatform.dto.UserDTO;

public interface UserService {
    UserDTO registerUser(UserDTO userDto);

    UserDTO userLogin(UserDTO dto);
}
