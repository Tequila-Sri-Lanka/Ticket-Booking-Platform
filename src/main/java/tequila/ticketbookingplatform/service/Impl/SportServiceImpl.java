package tequila.ticketbookingplatform.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tequila.ticketbookingplatform.dto.SportDTO;
import tequila.ticketbookingplatform.entity.SportEntity;
import tequila.ticketbookingplatform.repository.SportRepo;
import tequila.ticketbookingplatform.service.SportService;
import tequila.ticketbookingplatform.util.Mapping;

import java.util.List;
@Service
@Transactional
public class SportServiceImpl implements SportService {
    @Autowired
    private SportRepo sportRepo;
    @Autowired
    private Mapping mapping;
    @Override
    public SportDTO saveSport(SportDTO sportDTO) {
        SportEntity sportEntity = mapping.convertToSportEntity(sportDTO);
        SportEntity save = sportRepo.save(sportEntity);

        if (save==null){
            throw new RuntimeException("Failed to save the Movie");
        }
        return mapping.convertToSportDTO(save);
    }

    @Override
    public SportDTO getSportById(Long id) {
        SportEntity sport = sportRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Sport not found"));
        SportDTO sportDTO = mapping.convertToSportDTO(sport);
        return sportDTO;
    }

    @Override
    public List<SportDTO> getAllSports() {
        return mapping.convertSportToDTOList(sportRepo.findAll());
    }

    @Override
    public SportDTO updateSport(Long id,SportDTO sportDTO) {
        SportEntity sport= sportRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Sport not found"));
        sportDTO.setId(id);
        SportEntity sportEntity = mapping.convertToSportEntity(sportDTO);
        SportEntity updatedSport = sportRepo.save(sportEntity);
        return mapping.convertToSportDTO(updatedSport);
    }

    @Override
    public void deleteSport(Long id) {
        if (!sportRepo.existsById(id)) {
            throw new EntityNotFoundException("Sport not found");
        }
        sportRepo.deleteById(id);
    }
}
