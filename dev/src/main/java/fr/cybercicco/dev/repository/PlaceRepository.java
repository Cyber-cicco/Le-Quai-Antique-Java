package fr.cybercicco.dev.repository;

import fr.cybercicco.dev.entity.Place;
import fr.cybercicco.dev.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {

    @Query(
            value = """
    SELECT SUM(p.nb_places) FROM place p
    JOIN restaurant r ON p.restaurant_id = r.id
""",
            nativeQuery=true
    )
    Integer countAllPlaces(String restaurant);

    @Query(
            value = """
    SELECT p.id, p.nb_places, p.restaurant_id FROM place p 
    JOIN restaurant r ON p.restaurant_id = r.id
    WHERE p.id NOT IN (
        SELECT p2.id FROM reservation rn
        JOIN place p2 ON p2.id = rn.table_id
        JOIN restaurant r2 ON p2.restaurant_id = r2.id
        WHERE r2.nom_restaurant =:restaurant
        AND rn.date_reservation BETWEEN :debut AND :fin
    )
    AND r.nom_restaurant =:restaurant
""",
            nativeQuery=true
    )
    List<Place> findAllFreePlaces(LocalDateTime debut, LocalDateTime fin, String restaurant);
}
