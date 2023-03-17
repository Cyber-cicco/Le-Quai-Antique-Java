package fr.cybercicco.dev.repository;

import fr.cybercicco.dev.entity.Horaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HoraireRepository extends JpaRepository<Horaire, Integer> {

    @Query(
            value = """
    SELECT h.* FROM horaire h
    JOIN restaurant r ON h.restaurant_id = r.id
    WHERE h.jour_semaine = :jour
    AND
    r.nom_restaurant = :nom
""",
            nativeQuery=true
    )
    List<Horaire> getHoraireForCurrentDay(String jour, String nom);
}
