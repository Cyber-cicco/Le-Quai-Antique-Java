package fr.cybercicco.dev.repository;

import fr.cybercicco.dev.entity.Plat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlatRepository extends JpaRepository<Plat, Integer> {

    Optional<Plat> findByNomPlat(String nomPlat);

    boolean existsByNomPlat(String nomPlat);
}
