package fr.cybercicco.dev.controller;

import fr.cybercicco.dev.dto.HoraireDTO;
import fr.cybercicco.dev.dto.RestaurantDTO;
import fr.cybercicco.dev.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant/")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("horaires")
    public ResponseEntity<List<HoraireDTO>> getHoraires(@RequestParam String restaurant){
        return ResponseEntity.ok(restaurantService.getHorairesForRestaurant(restaurant));
    }

    @GetMapping("horaires_jour")
    public ResponseEntity<HoraireDTO> getHorairesCeJour(@RequestParam String restaurant, @RequestParam String day){
        log.info("restaurant : "+ restaurant);
        log.info("jour de la semaine : "+ day);
        return ResponseEntity.ok(restaurantService.getHoraireForWeekDay(restaurant, day));
    }

    @GetMapping("infos")
    public ResponseEntity<RestaurantDTO> getRestaurant(@RequestParam String restaurant){
        return ResponseEntity.ok(restaurantService.getRestaurant(restaurant));
    }

}
