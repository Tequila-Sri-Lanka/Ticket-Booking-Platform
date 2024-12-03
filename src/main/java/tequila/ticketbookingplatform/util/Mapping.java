package tequila.ticketbookingplatform.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tequila.ticketbookingplatform.dto.EventDTO;
import tequila.ticketbookingplatform.entity.EventEntity;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public Mapping(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

    }

    public EventEntity convertToEventEntity(EventDTO eventDTO) {
        return modelMapper.map(eventDTO, EventEntity.class);
    }

    public EventDTO convertToEventDTO(EventEntity eventEntity) {
        return modelMapper.map(eventEntity, EventDTO.class);
    }

    public List<EventDTO> convertEventToDTOList(List<EventEntity> eventEntities) {
        return modelMapper.map(eventEntities, new TypeToken<List<EventDTO>>() {
        }.getType());
    }

}
