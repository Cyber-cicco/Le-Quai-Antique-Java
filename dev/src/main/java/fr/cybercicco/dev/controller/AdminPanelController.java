package fr.cybercicco.dev.controller;

import fr.cybercicco.dev.dto.*;
import fr.cybercicco.dev.repository.FormuleRepository;
import fr.cybercicco.dev.repository.MenuRepository;
import fr.cybercicco.dev.repository.PlatRepository;
import fr.cybercicco.dev.service.MenuService;
import fr.cybercicco.dev.service.PhotoService;
import fr.cybercicco.dev.service.PlatService;
import fr.cybercicco.dev.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/")
@RequiredArgsConstructor
@Slf4j
public class AdminPanelController {

    private final MenuService menuService;

    private final PlatService platService;

    private final RestaurantService restaurantService;

    private final MenuRepository menuRepository;

    private final FormuleRepository formuleRepository;

    private final PlatRepository platRepository;

    @GetMapping("dashboard")
    public ResponseEntity<Map<String, String>> getAdminDashboard(){
        Map<String, String> response = new HashMap<>();
        response.put("message", "tu es authentifié admin");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("menu")
    public ResponseEntity<Map<String, Object>> changeMenu(
            @RequestBody MenuDTOPost menuDTO){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Menu bien inséré en base");
        response.put("menu", menuService.changeOneMenu(menuDTO));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("formule")
    public ResponseEntity<Map<String, Object>> changeFormule(@RequestBody FormuleDTO formuleDTO){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "formule bien insérée en base");
        response.put("formule", menuService.changeOneFormule(formuleDTO));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("plat")
    public ResponseEntity<Map<String, Object>> changePlat(@RequestBody PlatDTO platDTO){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "plat bien insérée en base");
        response.put("plat", platService.changeOnePlat(platDTO));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("restaurant")
    public ResponseEntity<Map<String, Object>> changeRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "informations du restaurant bien insérées en base");
        response.put("formule", restaurantService.changeOneRestaurant(restaurantDTO));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("horaire")
    public ResponseEntity<Map<String, Object>> changeRestaurant(@RequestBody HoraireDTO horaireDTO){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "horaire bien insérée en base");
        response.put("formule", restaurantService.changeOnehoraire(horaireDTO));
        return ResponseEntity.ok(response);
    }

    @GetMapping("menu/exists")
    public ResponseEntity<Map<String, Boolean>> checkIfNomMenuExists(@RequestParam String nom){
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", menuRepository.existsByNomMenu(nom));
        return ResponseEntity.ok(response);
    }

    @GetMapping("formule/exists")
    public ResponseEntity<Map<String, Boolean>> checkIfNomFormuleExists(@RequestParam String nom){
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", formuleRepository.existsByNomFormule(nom));
        return ResponseEntity.ok(response);
    }

    @GetMapping("plat/exists")
    public ResponseEntity<Map<String, Boolean>> checkIfNomPlatExists(@RequestParam String nom){
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", platRepository.existsByNomPlat(nom));
        return ResponseEntity.ok(response);
    }

    @PostMapping("photo")
    public ResponseEntity<Map<String, String>> postNewPhoto(@RequestParam Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        Map<String, String> response = new HashMap<>();
        response.put("photo", platService.uploadPhoto(id, file));
        return ResponseEntity.ok(response);
    }
}
