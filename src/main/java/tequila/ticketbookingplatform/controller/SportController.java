package tequila.ticketbookingplatform.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tequila.ticketbookingplatform.dto.SportDTO;
import tequila.ticketbookingplatform.service.SportService;

@RestController
@RequestMapping("api/v1/sports")
@CrossOrigin("*")
public class SportController {
    @Autowired
    private SportService sportService;
    @PostMapping
    public ResponseEntity<?> saveSport(@RequestBody SportDTO sportDTO) {
        try {
            SportDTO savedSport = sportService.saveSport(sportDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSport);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving sport: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSportById(@PathVariable Long id) {
        try {
            SportDTO sport = sportService.getSportById(id);
            return ResponseEntity.ok(sport);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sport not found");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllSports() {
        return ResponseEntity.ok(sportService.getAllSports());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSport(@PathVariable Long id, @RequestBody SportDTO sportDTO ) {
        try {
            SportDTO updatedSport = sportService.updateSport(id, sportDTO);
            return ResponseEntity.ok(updatedSport);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sport not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        try {
            sportService.deleteSport(id);
            return ResponseEntity.ok("Sport deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sport not found");
        }
    }
}
