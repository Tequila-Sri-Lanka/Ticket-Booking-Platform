package tequila.ticketbookingplatform.service;

import tequila.ticketbookingplatform.dto.EventDTO;
import tequila.ticketbookingplatform.entity.EventEntity;

import java.util.List;

public interface EventService {
    public EventDTO createEvent(EventDTO eventDTO);
    public EventDTO getEventById(Long id);
    public List<EventDTO> getAllEvents();
    public EventDTO updateEvent(Long id, EventDTO eventDTO);
    public void deleteEvent(Long id);
}
