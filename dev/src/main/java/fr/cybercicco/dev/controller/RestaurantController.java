package fr.cybercicco.dev.controller;

import fr.cybercicco.dev.dto.HoraireDTO;
import fr.cybercicco.dev.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant/")
@RequiredArgsConstructor
@CrossOrigin
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("horaires")
    public ResponseEntity<List<HoraireDTO>> getHoraires(@RequestParam String restaurant){
        return ResponseEntity.ok(restaurantService.getHorairesForRestaurant(restaurant));
    }

}
