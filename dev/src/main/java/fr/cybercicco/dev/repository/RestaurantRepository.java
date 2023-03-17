package fr.cybercicco.dev.repository;

import fr.cybercicco.dev.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Optional<Restaurant> findByNomRestaurant(String nomRestaurant);

    boolean existsByNomRestaurant(String nomRestaurant);
}
