package fr.cybercicco.dev.controller;

import fr.cybercicco.dev.dto.MenuDTOPost;
import fr.cybercicco.dev.repository.MenuRepository;
import fr.cybercicco.dev.service.MenuService;
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
    private final MenuRepository menuRepository;

    @GetMapping("dashboard")
    public ResponseEntity<?> getAdminDashboard(){
        return ResponseEntity.ok(new HashMap<>().put("message", "tu es authentifié admin"));
    }

    @PatchMapping("menu")
    public ResponseEntity<Map<String, Object>> changeMenu(
            @RequestBody MenuDTOPost menuDTO){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Menu bien inséré en base");
        response.put("menu", menuService.changeOneMenu(menuDTO));
        return ResponseEntity.ok(response);
    }

    @GetMapping("menu/check")
    public ResponseEntity<Map<String, Boolean>> checkIfNomMenuExists(@RequestParam String nom){
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", menuRepository.existsByNomMenu(nom));
        return ResponseEntity.ok(response);
    }
}
