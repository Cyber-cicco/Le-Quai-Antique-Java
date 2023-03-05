package fr.cybercicco.dev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminPanelController {

    public ResponseEntity<?> getAdminDashboard(){
        return ResponseEntity.ok("tu es authentifié admin");
    }
}