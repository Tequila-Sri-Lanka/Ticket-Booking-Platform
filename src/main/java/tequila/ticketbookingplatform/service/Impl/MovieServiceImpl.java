package tequila.ticketbookingplatform.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tequila.ticketbookingplatform.dto.MovieDTO;
import tequila.ticketbookingplatform.entity.MovieEntity;
import tequila.ticketbookingplatform.repository.MovieRepo;
import tequila.ticketbookingplatform.service.MovieService;
import tequila.ticketbookingplatform.service.S3Service;
import tequila.ticketbookingplatform.util.AppUtill;
import tequila.ticketbookingplatform.util.Mapping;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private Mapping mapping;
    private final S3Service s3Service;
    private final String folderName="movie-images";
    String fileExt = "movie.png";
    @Autowired
    public MovieServiceImpl(MovieRepo movieRepo,Mapping mapping,S3Service s3Service) {
        this.movieRepo=movieRepo;
        this.mapping=mapping;
        this.s3Service = s3Service;
    }


    @Override
    public MovieDTO saveMovie(MovieDTO movieDTO) {
        File image;
        String uploadedImageId = null;
        try {
             image= AppUtill.convertBase64ToImage(movieDTO.getImage(), fileExt);
            uploadedImageId= s3Service.uploadFile(image, folderName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        movieDTO.setImage(uploadedImageId);

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
        File updatedImage;
        MovieEntity movie = movieRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        movieDTO.setId(id);
        if (movieDTO.getImage()!=null){
            try {
                updatedImage= AppUtill.convertBase64ToImage(movieDTO.getImage(), fileExt);
                String updatedImageId = s3Service.updateFile(updatedImage, movie.getImage(), folderName);
                movieDTO.setImage(updatedImageId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        MovieEntity movieEntity = mapping.convertToMovieEntity(movieDTO);
        MovieEntity save = movieRepo.save(movieEntity);
        return mapping.convertToMovieDTO(save);
    }

    @Override
    public void deleteMovie(Long id) {
        if (!movieRepo.existsById(id)) {
            throw new EntityNotFoundException("Movie not found");
        }
        Optional<MovieEntity> movieForRemove= movieRepo.findById(id);
        boolean imageDeleted = s3Service.deleteFile(movieForRemove.get().getImage(), folderName);
        if (imageDeleted){
            movieRepo.deleteById(id);
        }else {
            throw new RuntimeException("movie can not delete try again");
        }


    }
}
