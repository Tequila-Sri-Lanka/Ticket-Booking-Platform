package tequila.ticketbookingplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tequila.ticketbookingplatform.dto.UserDTO;
import tequila.ticketbookingplatform.service.UserService;
import tequila.ticketbookingplatform.util.JWTTokenGenerator;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class AuthController {

    private final UserService userService;

    private final JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    public AuthController(UserService userService, JWTTokenGenerator jwtTokenGenerator) {
        this.userService = userService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserDTO userDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            UserDTO dto = this.userService.registerUser(userDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("TOKEN_INVALID", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public Map<String, String> postLogin(@RequestBody UserDTO dto) {
        UserDTO user = userService.userLogin(dto);
        Map<String, String> response = new HashMap<>();
        if (user == null) {
            response.put("massage", "wrong details");
        } else {
            String token = this.jwtTokenGenerator.generateJwtToken(user);
            response.put("token", token);
        }
        return response;
    }

}