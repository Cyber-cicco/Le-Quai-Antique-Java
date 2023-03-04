package fr.cybercicco.dev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("connexion")
public class ConnexionController {

    @GetMapping
    public ResponseEntity<?> createSession(){
        return ResponseEntity.ok("you are on the connexion page");
    }

}
