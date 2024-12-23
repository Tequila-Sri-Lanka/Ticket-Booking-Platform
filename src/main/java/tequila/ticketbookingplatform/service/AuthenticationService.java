package lk.ijse.green_shadow_backend.service;


import lk.ijse.green_shadow_backend.dao.UserDao;
import lk.ijse.green_shadow_backend.dto.UserDto;
import lk.ijse.green_shadow_backend.jwtmodels.JwtAuthResponse;
import lk.ijse.green_shadow_backend.jwtmodels.SignIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public interface AuthenticationService {
    JwtAuthResponse signIn(SignIn signIn);
    JwtAuthResponse signUp(UserDto signUp);
    JwtAuthResponse refreshToken(String accessToken);
}
