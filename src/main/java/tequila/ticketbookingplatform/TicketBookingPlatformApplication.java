package tequila.ticketbookingplatform;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tequila.ticketbookingplatform.entity.UserEntity;
import tequila.ticketbookingplatform.repository.UserRepo;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class TicketBookingPlatformApplication {
    private static final Logger logger = LoggerFactory.getLogger(TicketBookingPlatformApplication.class);
    private final UserRepo userRepo;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    public TicketBookingPlatformApplication(UserRepo userRepo){
        this.userRepo=userRepo;
    }
    public static void main(String[] args) {
        SpringApplication.run(TicketBookingPlatformApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
