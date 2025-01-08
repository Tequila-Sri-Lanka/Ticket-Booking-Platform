package tequila.ticketbookingplatform.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tequila.ticketbookingplatform.dto.TicketBookingDTO;
import tequila.ticketbookingplatform.service.TicketBookingService;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class TicketBookingController {

    private final TicketBookingService service;

    public TicketBookingController(TicketBookingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> bookTicket(@RequestBody TicketBookingDTO dto) {
        try { TicketBookingDTO booking = service.bookTicket(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(booking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating Booking : " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<TicketBookingDTO>> getAllBookings() {
        return ResponseEntity.ok(service.getAllBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {

        try {
            TicketBookingDTO booking = service.getBookingById(id);
            return ResponseEntity.ok(booking);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        try {
            service.deleteBooking(id);
            return ResponseEntity.ok("Booking deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
        }
    }
}
