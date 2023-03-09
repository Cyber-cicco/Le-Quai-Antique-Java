package fr.cybercicco.dev.controller;

import fr.cybercicco.dev.dto.UtilisateurDTO;
import fr.cybercicco.dev.service.JwtService;
import fr.cybercicco.dev.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/")
@Slf4j
public class UserController {

    private final JwtService jwtService;
    private final UtilisateurService utilisateurService;

    @GetMapping("test_admin")
    public ResponseEntity<String> getInfoDashboard(){
        return ResponseEntity.ok("tu es authentifié mais pas admin");
    }


    @GetMapping("profil")
    public ResponseEntity<UtilisateurDTO> getProfilUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization){
        return ResponseEntity.ok(utilisateurService.getUtilisateurByEmail(jwtService.extractEmail(authorization.substring(7))));

    }
}
