package fr.cybercicco.dev.repository;

import fr.cybercicco.dev.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Integer countReservationByDateReservationBetween(LocalDateTime debut, LocalDateTime fin);

}
