package fr.cybercicco.dev.repository;

import fr.cybercicco.dev.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(
            value = """
    SELECT SUM(p.nb_places) FROM place p
    JOIN reservation rn ON rn.table_id = p.id
    JOIN restaurant rt ON p.restaurant_id = rt.id
    WHERE rt.nom_restaurant =:restaurant
    AND rn.date_reservation BETWEEN :debut AND :dateFin
""",
            nativeQuery=true
    )
    Integer getNumberOfTakenPlaces(String restaurant, LocalDateTime debut, LocalDateTime dateFin);

}
