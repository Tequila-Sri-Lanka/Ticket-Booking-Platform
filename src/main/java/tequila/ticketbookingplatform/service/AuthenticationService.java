package tequila.ticketbookingplatform.service;
import org.springframework.stereotype.Service;
import tequila.ticketbookingplatform.dto.UserDto;
import tequila.ticketbookingplatform.jwtmodels.JwtAuthResponse;
import tequila.ticketbookingplatform.jwtmodels.SignIn;


@Service
public interface AuthenticationService {
    JwtAuthResponse signIn(SignIn signIn);
    JwtAuthResponse signUp(UserDto signUp);
    JwtAuthResponse refreshToken(String accessToken);
}
