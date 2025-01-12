package tequila.ticketbookingplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDTO {
    private Long id;

    private String title;
    private String genre;
    private String director;
    private String releaseDate;
    private Double rating;
    private double playTime;
    private String description;
}
