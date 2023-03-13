package fr.cybercicco.dev.repository;

import fr.cybercicco.dev.entity.Place;
import fr.cybercicco.dev.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    List<Place> findByRestaurant(Restaurant restaurant);
}
