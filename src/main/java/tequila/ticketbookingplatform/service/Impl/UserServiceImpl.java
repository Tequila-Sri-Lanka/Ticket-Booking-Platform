package tequila.ticketbookingplatform.service.Impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tequila.ticketbookingplatform.dto.UserDTO;
import tequila.ticketbookingplatform.entity.UserEntity;
import tequila.ticketbookingplatform.repository.UserRepo;
import tequila.ticketbookingplatform.service.UserService;


import java.util.List;

@Service
@Transactional
public class UserServiceImpl  implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO registerUser(UserDTO userDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserEntity user = this.dtoToEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity save = this.userRepo.save(user);
        return entityToDto(save);
    }

    @Override
    public UserDTO userLogin(UserDTO dto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<UserEntity> userNames = userRepo.findByUserName(dto.getUserName());
        for (UserEntity name : userNames) {
            boolean isPasswordMatches = passwordEncoder.matches(dto.getPassword(), name.getPassword());
            if (isPasswordMatches) {
                return entityToDto(name);
            }
        }
        return null;
    }


    private UserEntity dtoToEntity(UserDTO userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

    private UserDTO entityToDto(UserEntity user) {
        return (user == null) ? null : modelMapper.map(user, UserDTO.class);
    }
}
