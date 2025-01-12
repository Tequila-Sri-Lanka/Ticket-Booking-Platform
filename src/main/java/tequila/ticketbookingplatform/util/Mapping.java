package tequila.ticketbookingplatform.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tequila.ticketbookingplatform.dto.*;
import tequila.ticketbookingplatform.entity.*;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public Mapping(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

    }
    //ticket mapping
    public TicketEntity convertToTicketEntity(TicketDTO ticketDTO) {
        return modelMapper.map(ticketDTO, TicketEntity.class);
    }

    public TicketDTO convertToTicketDTO(TicketEntity ticket) {
        return modelMapper.map(ticket, TicketDTO.class);
    }

    public List<TicketDTO> convertTicketToDTOList(List<TicketEntity> ticketEntities) {
        return modelMapper.map(ticketEntities, new TypeToken<List<TicketDTO>>() {
        }.getType());
    }
//event mapping
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
    //user mapping
    public UserEntity convertToUserEntity(UserDto userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);

    }

    public UserDto convertTouserDTO(UserEntity userEntity) {

        return modelMapper.map(userEntity, UserDto.class);
    }

    public List<UserDto> convertUserToDTOList(List<UserEntity> userEntities) {
        return modelMapper.map(userEntities, new TypeToken<List<UserDto>>() {
        }.getType());
    }

    //movies mapping
    public MovieEntity convertToMovieEntity(MovieDTO movieDTO) {
        return modelMapper.map(movieDTO, MovieEntity.class);

    }

    public MovieDTO convertToMovieDTO(MovieEntity movieEntity) {

        return modelMapper.map(movieEntity, MovieDTO.class);
    }

    public List<MovieDTO> convertMovieToDTOList(List<MovieEntity> movieEntities) {
        return modelMapper.map(movieEntities, new TypeToken<List<MovieDTO>>() {
        }.getType());
    }
    //sport mapping
    public SportEntity convertToSportEntity(SportDTO sportDTO) {
        return modelMapper.map(sportDTO, SportEntity.class);

    }

    public SportDTO convertToSportDTO(SportEntity sportEntity) {

        return modelMapper.map(sportEntity, SportDTO.class);
    }

    public List<SportDTO> convertSportToDTOList(List<SportEntity> sportEntities) {
        return modelMapper.map(sportEntities, new TypeToken<List<SportDTO>>() {
        }.getType());
    }

}
