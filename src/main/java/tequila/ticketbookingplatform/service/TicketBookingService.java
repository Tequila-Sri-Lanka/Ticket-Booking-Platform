package tequila.ticketbookingplatform.service;

import tequila.ticketbookingplatform.dto.TicketBookingDTO;
import tequila.ticketbookingplatform.entity.TicketBookingEntity;

import java.util.List;

public interface TicketBookingService {
    public TicketBookingDTO bookTicket(TicketBookingDTO dto);
    public List<TicketBookingDTO> getAllBookings();
    public TicketBookingDTO getBookingById(Long id);
    public void deleteBooking(Long id);
}
