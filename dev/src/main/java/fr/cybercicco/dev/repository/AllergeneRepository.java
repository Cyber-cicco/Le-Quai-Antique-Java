package fr.cybercicco.dev.repository;

import fr.cybercicco.dev.entity.Allergene;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AllergeneRepository extends JpaRepository<Allergene, Integer> {

    Optional<Allergene> findByNomAllergene(String nomAllergene);
}
