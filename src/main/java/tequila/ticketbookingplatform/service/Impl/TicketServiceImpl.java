package tequila.ticketbookingplatform.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tequila.ticketbookingplatform.dto.TicketDTO;
import tequila.ticketbookingplatform.entity.TicketEntity;
import tequila.ticketbookingplatform.repository.TicketRepo;
import tequila.ticketbookingplatform.service.TicketService;
import tequila.ticketbookingplatform.util.Mapping;

import java.util.List;
@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private TicketRepo ticketRepo;

    private Mapping mapping;
    @Autowired
    public TicketServiceImpl(TicketRepo ticketRepo, Mapping mapping) {
        this.ticketRepo = ticketRepo;
        this.mapping = mapping;
    }

    @Override
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        System.out.println(ticketDTO.getTicketId());
        TicketEntity ticket = mapping.convertToTicketEntity(ticketDTO);

        System.out.println();
        TicketEntity savedTicket = ticketRepo.save(ticket);
        TicketDTO result = mapping.convertToTicketDTO(savedTicket);
        return result;
    }

    @Override
    public TicketDTO getTicketById(Long id) {
        TicketEntity ticket = ticketRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Event not found"));
       TicketDTO ticketDTO = mapping.convertToTicketDTO(ticket);
        return ticketDTO;
    }

    @Override
    public List<TicketDTO> getAllTickets() {
        return mapping.convertTicketToDTOList(ticketRepo.findAll());
    }

    @Override
    public TicketDTO updateTicket(Long id, TicketDTO ticketDTO) {
        TicketEntity ticket = ticketRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Event not found"));
        ticketDTO.setTicketId(ticket.getTicketId());
       TicketEntity result = mapping.convertToTicketEntity(ticketDTO);
        TicketEntity updatedTicket = ticketRepo.save(result);
        return mapping.convertToTicketDTO(updatedTicket);
    }

    @Override
    public void deleteTicket(Long id) {
        if (!ticketRepo.existsById(id)) {
            throw new EntityNotFoundException("Event not found");
        }
        ticketRepo.deleteById(id);
    }
}
