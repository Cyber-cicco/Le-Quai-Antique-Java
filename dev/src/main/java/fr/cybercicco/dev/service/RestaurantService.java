package fr.cybercicco.dev.service;

import fr.cybercicco.dev.dto.HoraireDTO;
import fr.cybercicco.dev.dto.HoraireMapper;
import fr.cybercicco.dev.dto.RestaurantDTO;
import fr.cybercicco.dev.dto.RestaurantMapper;
import fr.cybercicco.dev.entity.Restaurant;
import fr.cybercicco.dev.exception.DuplicateEntryException;
import fr.cybercicco.dev.repository.HoraireRepository;
import fr.cybercicco.dev.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Validated
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final RestaurantMapper restaurantMapper;
    private final HoraireRepository horaireRepository;
    private final HoraireMapper horaireMapper;


    public List<HoraireDTO> getHorairesForRestaurant(String nom) {
        Restaurant restaurant = restaurantRepository.findByNomRestaurant(nom).orElseThrow(EntityNotFoundException::new);
        return restaurant.getHoraires().stream().map(horaireMapper::toHoraireDTO).toList();
    }

    public HoraireDTO getHoraireForWeekDay(String restaurant, String weekDay) {
        return horaireMapper.toHoraireDTO(horaireRepository.getHoraireForCurrentDay(weekDay, restaurant).get(0));
    }

    public RestaurantDTO getRestaurant(String restaurant) {
        return restaurantMapper.toRestaurantDTO(
                restaurantRepository.findByNomRestaurant(restaurant).orElseThrow(EntityNotFoundException::new));
    }

    public RestaurantDTO changeOneRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantRepository.findByNomRestaurant(restaurantDTO.getNomRestaurant()).orElseThrow(EntityNotFoundException::new);
        if(restaurantRepository.existsByNomRestaurant(restaurantDTO.getNomRestaurant())
                &&
                !Objects.equals(restaurantDTO.getNomRestaurant(), restaurant.getNomRestaurant())){
            throw new DuplicateEntryException("Le nom de la formule doit être unique");
        }
        restaurant.setDescription(restaurantDTO.getDescription());
        restaurant.setMaxConvivesAutorises(restaurantDTO.getMaxConvivesAutorises());
        restaurantRepository.save(restaurant);
        return restaurantMapper.toRestaurantDTO(restaurant);
    }
}
