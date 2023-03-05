package fr.cybercicco.dev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/infos/")
public class UserPanelController {

    @GetMapping
    public ResponseEntity<String> getInfoDashboard(){
        return ResponseEntity.ok("tu es authentifié mais pas admin");
    }
}
