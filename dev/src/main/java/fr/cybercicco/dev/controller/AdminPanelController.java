package fr.cybercicco.dev.controller;

import fr.cybercicco.dev.dto.FormuleDTO;
import fr.cybercicco.dev.dto.MenuDTOPost;
import fr.cybercicco.dev.dto.PlatDTO;
import fr.cybercicco.dev.repository.FormuleRepository;
import fr.cybercicco.dev.repository.MenuRepository;
import fr.cybercicco.dev.repository.PlatRepository;
import fr.cybercicco.dev.service.MenuService;
import fr.cybercicco.dev.service.PlatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/")
@RequiredArgsConstructor
@Slf4j
public class AdminPanelController {

    private final MenuService menuService;

    private final PlatService platService;

    private final MenuRepository menuRepository;

    private final FormuleRepository formuleRespository;

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
        response.put("formule", platService.changeOnePlat(platDTO));
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
        response.put("exists", formuleRespository.existsByNomFormule(nom));
        return ResponseEntity.ok(response);
    }

    @GetMapping("plat/exists")
    public ResponseEntity<Map<String, Boolean>> checkIfNomPlatExists(@RequestParam String nom){
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", platRepository.existsByNomPlat(nom));
        return ResponseEntity.ok(response);
    }
}
