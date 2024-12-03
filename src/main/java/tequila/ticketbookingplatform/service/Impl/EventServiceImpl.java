package tequila.ticketbookingplatform.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tequila.ticketbookingplatform.dto.EventDTO;
import tequila.ticketbookingplatform.entity.EventEntity;
import tequila.ticketbookingplatform.repository.EventRepo;
import tequila.ticketbookingplatform.service.EventService;
import tequila.ticketbookingplatform.util.Mapping;

import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepo eventRepository;
    @Autowired
    private Mapping mapping;

    @Override
    public EventDTO createEvent(EventDTO eventDTO) {
        System.out.println(eventDTO.getEventName());
        EventEntity event = mapping.convertToEventEntity(eventDTO);

        System.out.println();
        EventEntity savedEvent = eventRepository.save(event);
        EventDTO result = mapping.convertToEventDTO(savedEvent);
        return result;
    }

    @Override
    public EventDTO getEventById(Long id) {
        EventEntity event = eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Event not found"));
        EventDTO eventDTO = mapping.convertToEventDTO(event);
        return eventDTO;
    }

    @Override
    public List<EventDTO> getAllEvents() {
        return mapping.convertEventToDTOList(eventRepository.findAll());
    }

    @Override
    public EventDTO updateEvent(Long id, EventDTO eventDTO) {
        EventEntity event = eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Event not found"));
        eventDTO.setEventId(id);
        EventEntity result = mapping.convertToEventEntity(eventDTO);
        EventEntity updatedEvent = eventRepository.save(result);
        return mapping.convertToEventDTO(result);
    }

    @Override
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new EntityNotFoundException("Event not found");
        }
        eventRepository.deleteById(id);
    }
}
