package tequila.ticketbookingplatform.service;
import tequila.ticketbookingplatform.dto.SportDTO;

import java.util.List;

public interface SportService {
    public SportDTO saveSport(SportDTO sportDTO);
    public SportDTO getSportById(Long id);
    public List<SportDTO> getAllSports();
    public SportDTO updateSport(Long id, SportDTO sportDTO);
    public void deleteSport(Long id);
}
