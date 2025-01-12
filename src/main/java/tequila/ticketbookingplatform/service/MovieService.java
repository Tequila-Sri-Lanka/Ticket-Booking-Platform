package tequila.ticketbookingplatform.service;

import tequila.ticketbookingplatform.dto.EventDTO;
import tequila.ticketbookingplatform.dto.MovieDTO;

import java.util.List;

public interface MovieService {
    public MovieDTO saveMovie(MovieDTO movieDTO);
    public MovieDTO getMovieById(Long id);
    public List<MovieDTO> getAllMovies();
    public MovieDTO updateMovie(Long id, MovieDTO movieDTO);
    public void deleteMovie(Long id);
}
