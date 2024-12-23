package tequila.ticketbookingplatform.service;


import tequila.ticketbookingplatform.dto.TicketDTO;

import java.util.List;

public interface TicketService {
    public TicketDTO createTicket(TicketDTO ticketDTO);
    public TicketDTO getTicketById(Long id);
    public List<TicketDTO> getAllTickets();
    public TicketDTO updateTicket(Long id,TicketDTO ticketDTO);
    public void deleteTicket(Long id);
}
