package tequila.ticketbookingplatform.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tequila.ticketbookingplatform.dto.TicketBookingDTO;
import tequila.ticketbookingplatform.entity.TicketBookingEntity;
import tequila.ticketbookingplatform.entity.TicketEntity;
import tequila.ticketbookingplatform.entity.UserEntity;
import tequila.ticketbookingplatform.repository.TicketBookingRepository;
import tequila.ticketbookingplatform.repository.TicketRepo;
import tequila.ticketbookingplatform.repository.UserRepo;
import tequila.ticketbookingplatform.service.TicketBookingService;
import tequila.ticketbookingplatform.util.Mapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class TicketBookingServiceImpl implements TicketBookingService {

    private final TicketBookingRepository bookingRepository;
    private final UserRepo userRepo;
    private final TicketRepo ticketRepo;

    private Mapping mapping;
    @Autowired
    public TicketBookingServiceImpl(TicketBookingRepository bookingRepository,UserRepo userRepo, TicketRepo ticketRepo) {
        this.bookingRepository = bookingRepository;
        this.userRepo = userRepo;
        this.ticketRepo = ticketRepo;
    }

    public TicketBookingDTO bookTicket(TicketBookingDTO dto) {
        // Fetch User instead of Customer
        UserEntity user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch Ticket
        TicketEntity ticket = ticketRepo.findById(dto.getTicketId())
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.getQty() < dto.getQuantity()) {
            throw new RuntimeException("Not enough tickets available");
        }

        ticket.setQty(ticket.getQty() - dto.getQuantity());
        ticketRepo.save(ticket);
        // Create and save TicketBooking
        TicketBookingEntity booking = new TicketBookingEntity();
        booking.setUser(user); // Replace setCustomer with setUser
        booking.setTicket(ticket);
        booking.setQuantity(dto.getQuantity());
        booking.setTotalPrice(ticket.getPrice() * dto.getQuantity());
        booking.setBookingDate(LocalDateTime.now());

        TicketBookingEntity savedBooking = bookingRepository.save(booking);
        System.out.println(savedBooking.getBookingDate());
        System.out.println(savedBooking.getTicket().getTicketId());
        System.out.println(savedBooking.getUser().getName());
        System.out.println(savedBooking.getQuantity());
        System.out.println(savedBooking.getTotalPrice());
        if (savedBooking == null){
            throw new RuntimeException("Failed to save the booking");
        }
        return convertToBookingDTO(savedBooking);

    }

    public List<TicketBookingDTO> getAllBookings() {
        return convertBookingToDTOList(bookingRepository.findAll()) ;
    }

    public TicketBookingDTO getBookingById(Long id) {
        TicketBookingEntity booking= bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

       return convertToBookingDTO(booking);
    }

    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new EntityNotFoundException("Booking not found");
        }
        bookingRepository.deleteById(id);
    }

    private TicketBookingDTO convertToBookingDTO (TicketBookingEntity ticketBookingEntity){
        TicketBookingDTO ticketBookingDTO = new TicketBookingDTO();
        ticketBookingDTO.setId(ticketBookingEntity.getId());
        ticketBookingDTO.setUserId(ticketBookingEntity.getUser().getId());
        ticketBookingDTO.setTicketId(ticketBookingEntity.getTicket().getTicketId());
        ticketBookingDTO.setQuantity(ticketBookingEntity.getQuantity());
        ticketBookingDTO.setBookingDate(ticketBookingEntity.getBookingDate());
        ticketBookingDTO.setTotalPrice(ticketBookingEntity.getTotalPrice());

        return ticketBookingDTO;
    }
    private List<TicketBookingDTO> convertBookingToDTOList(List<TicketBookingEntity> bookingEntities){
        List<TicketBookingDTO> bookingDTOList = new ArrayList<>();

        for (TicketBookingEntity entity : bookingEntities) {
            bookingDTOList.add(convertToBookingDTO(entity));
        }

        return bookingDTOList;
    }


}
