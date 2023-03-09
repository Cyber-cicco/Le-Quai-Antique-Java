package fr.cybercicco.dev.repository;

import fr.cybercicco.dev.entity.Horaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HoraireRepository extends JpaRepository<Horaire, Integer> {

    @Query(
            value = """
    SELECT h.* FROM horaire h 
    JOIN restaurant_horaires rh ON h.id = rh.horaire_id
    JOIN restaurant r ON rh.restaurant_id = r.id
    WHERE h.jour_semaine = :jour
    AND
    r.nom_restaurant = :nom
""",
            nativeQuery=true
    )
    Optional<List<Horaire>> getHoraireForCurrentDay(String jour, String nom);
}
