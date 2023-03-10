package fr.cybercicco.dev.controller;

import fr.cybercicco.dev.controller.message.AuthenticationRequest;
import fr.cybercicco.dev.controller.message.AuthenticationResponse;
import fr.cybercicco.dev.controller.message.RegisterRequest;
import fr.cybercicco.dev.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/connexion/")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class ConnexionController {

    private final AuthenticationService authenticationService;

    @PostMapping("isconnected")
    public ResponseEntity<AuthenticationResponse> getSession(){
        return ResponseEntity.ok(new AuthenticationResponse());
    }


    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
