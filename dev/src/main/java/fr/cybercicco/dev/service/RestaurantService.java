package fr.cybercicco.dev.service;

import fr.cybercicco.dev.dto.HoraireDTO;
import fr.cybercicco.dev.dto.HoraireMapper;
import fr.cybercicco.dev.dto.RestaurantDTO;
import fr.cybercicco.dev.dto.RestaurantMapper;
import fr.cybercicco.dev.entity.Horaire;
import fr.cybercicco.dev.entity.Restaurant;
import fr.cybercicco.dev.repository.HoraireRepository;
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
        restaurant.setDescription(restaurantDTO.getDescription());
        restaurant.setMaxConvivesAutorises(restaurantDTO.getMaxConvivesAutorises());
        restaurantRepository.save(restaurant);
        return restaurantMapper.toRestaurantDTO(restaurant);
    }

    public HoraireDTO changeOnehoraire(HoraireDTO horaireDTO) {
        Horaire horaire = horaireRepository.findById(horaireDTO.getId()).orElseThrow(EntityNotFoundException::new);
        horaire.setOuvertDejeuner(horaireDTO.getOuvertDejeuner());
        horaire.setOuvertureDejeuner(horaireDTO.getOuvertureDejeuner());
        horaire.setFermetureDejeuner(horaireDTO.getFermetureDejeuner());
        horaire.setOuvertDiner(horaireDTO.getOuvertDiner());
        horaire.setOuvertureDiner(horaireDTO.getOuvertureDiner());
        horaire.setFermetureDiner(horaireDTO.getFermetureDiner());
        horaireRepository.save(horaire);
        return horaireMapper.toHoraireDTO(horaire);
    }
}
