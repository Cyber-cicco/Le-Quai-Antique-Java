package fr.cybercicco.dev.repository;

import fr.cybercicco.dev.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
}
