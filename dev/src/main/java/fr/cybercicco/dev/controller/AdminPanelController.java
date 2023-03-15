package fr.cybercicco.dev.controller;

import fr.cybercicco.dev.dto.MenuDTOGet;
import fr.cybercicco.dev.dto.MenuDTOPost;
import fr.cybercicco.dev.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/")
@RequiredArgsConstructor
public class AdminPanelController {

    private final MenuService menuService;

    @GetMapping("dashboard")
    public ResponseEntity<?> getAdminDashboard(){
        return ResponseEntity.ok(new HashMap<>().put("message", "tu es authentifié admin"));
    }

    @PatchMapping("menu")
    public ResponseEntity<Map<String, String>> changeMenu(@RequestBody MenuDTOPost menuDTO){
        Map<String, String> response = new HashMap<>();
        response.put("message", menuService.changeOneMenu(menuDTO));
        return ResponseEntity.ok(response);
    }
}
