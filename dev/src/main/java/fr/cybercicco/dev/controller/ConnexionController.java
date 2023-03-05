package fr.cybercicco.dev.controller;

import fr.cybercicco.dev.controller.message.AuthenticationRequest;
import fr.cybercicco.dev.controller.message.AuthenticationResponse;
import fr.cybercicco.dev.controller.message.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("connexion")
public class ConnexionController {

    @GetMapping
    public ResponseEntity<?> getSession(){
        return ResponseEntity.ok("you are on the connexion page");
    }

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return null;
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return null;
    }

}
