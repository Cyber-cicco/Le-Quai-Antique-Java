package fr.cybercicco.dev.controller;

import fr.cybercicco.dev.controller.message.ReservationResponse;
import fr.cybercicco.dev.dto.ReservationDTO;
import fr.cybercicco.dev.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<ReservationResponse> getPlacesDisponibles(@RequestParam String date, @RequestParam Boolean soir, @RequestParam String restaurant){
        return ResponseEntity.ok(reservationService.getReservationsForCurrentDay(date, soir, restaurant));
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> postNewReservation(
            @RequestBody ReservationDTO reservationDTO,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization){
        return ResponseEntity.ok(reservationService.saveReservations(reservationDTO, authorization));
    }
}
