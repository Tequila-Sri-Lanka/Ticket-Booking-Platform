package tequila.ticketbookingplatform.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tequila.ticketbookingplatform.dto.MovieDTO;
import tequila.ticketbookingplatform.entity.MovieEntity;
import tequila.ticketbookingplatform.repository.MovieRepo;
import tequila.ticketbookingplatform.service.MovieService;
import tequila.ticketbookingplatform.util.Mapping;

import java.util.List;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private Mapping mapping;


    @Override
    public MovieDTO saveMovie(MovieDTO movieDTO) {
        MovieEntity movieEntity = mapping.convertToMovieEntity(movieDTO);
        MovieEntity save = movieRepo.save(movieEntity);
        if (save==null){
            throw new RuntimeException("Failed to save the Movie");
        }
       return mapping.convertToMovieDTO(save);

    }

    @Override
    public MovieDTO getMovieById(Long id) {
        MovieEntity movie = movieRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        MovieDTO movieDTO = mapping.convertToMovieDTO(movie);
        return movieDTO;
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        return mapping.convertMovieToDTOList(movieRepo.findAll());
    }

    @Override
    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {
        MovieEntity movie = movieRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        movieDTO.setId(id);
        MovieEntity movieEntity = mapping.convertToMovieEntity(movieDTO);
        MovieEntity save = movieRepo.save(movieEntity);
        return mapping.convertToMovieDTO(save);
    }

    @Override
    public void deleteMovie(Long id) {
        if (!movieRepo.existsById(id)) {
            throw new EntityNotFoundException("Movie not found");
        }
        movieRepo.deleteById(id);
    }
}
