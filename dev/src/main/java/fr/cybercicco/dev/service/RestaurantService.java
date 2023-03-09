package fr.cybercicco.dev.service;

import fr.cybercicco.dev.dto.HoraireDTO;
import fr.cybercicco.dev.dto.HoraireMapper;
import fr.cybercicco.dev.entity.Restaurant;
import fr.cybercicco.dev.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final HoraireMapper horaireMapper;


    public List<HoraireDTO> getHorairesForRestaurant(String nom) {
        Restaurant restaurant = restaurantRepository.findByNomRestaurant(nom).orElseThrow(EntityNotFoundException::new);
        return restaurant.getHoraires().stream().map(horaireMapper::toHoraireDTO).toList();
    }
}
