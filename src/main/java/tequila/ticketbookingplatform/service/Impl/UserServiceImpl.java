package tequila.ticketbookingplatform.service.Impl;

import jakarta.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;
import tequila.ticketbookingplatform.dto.UserDto;
import tequila.ticketbookingplatform.entity.UserEntity;
import tequila.ticketbookingplatform.exception.DataPersistFailedException;
import tequila.ticketbookingplatform.exception.NotFoundException;
import tequila.ticketbookingplatform.repository.UserRepo;
import tequila.ticketbookingplatform.service.UserService;
import tequila.ticketbookingplatform.util.Mapping;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl  implements UserService {
    private final UserRepo userDAO;
    private final Mapping mapping;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, UserRepo userDAO, ModelMapper modelMapper, Mapping mapping) {

        this.userDAO = userRepo;

        this.mapping = mapping;
    }
    @Override
    public void saveUser(UserDto userDTO) {
        if (userDTO.getEmail() == null) {
            throw new NotFoundException(" Email are required.");
        }
        UserEntity savedUser = userDAO.save(mapping.convertToUserEntity(userDTO));
        if(savedUser == null){
            throw new DataPersistFailedException("Cannot save user");
        }
    }


    @Override
    public void deleteUser(Long id) {
        if (!userDAO.existsById(id)) {
            throw new NotFoundException("Cannot delete: User with ID " + id + " not found.");
        }
        userDAO.deleteById(id);
    }

    @Override
    public void updateUser(Long id, UserDto updatedUser) {
        Optional<UserEntity> tmpUser = userDAO.findById(id);

        if (tmpUser.isEmpty()) {
            throw new NotFoundException("User not found");
        } else {
            UserEntity userEntity = mapping.convertToUserEntity(updatedUser);
            userEntity.setId(id);
            userDAO.save(userEntity);
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        return mapping.convertUserToDTOList(userDAO.findAll());
    }


    @Override
    public UserDetailsService userDetailsService() {
        return email ->
                userDAO.findByEmail(email)
                        .orElseThrow(()-> new NotFoundException("User Not found"));
    }




}
