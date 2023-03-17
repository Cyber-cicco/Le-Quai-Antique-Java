package fr.cybercicco.dev.repository;

import fr.cybercicco.dev.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    boolean existsByNomMenu(String nomMenu);

    Optional<Menu> findByNomMenu(String nomMenu);

}
