package fr.cybercicco.dev.service;

import fr.cybercicco.dev.controller.message.AuthenticationRequest;
import fr.cybercicco.dev.controller.message.AuthenticationResponse;
import fr.cybercicco.dev.controller.message.RegisterRequest;
import fr.cybercicco.dev.entity.Utilisateur;
import fr.cybercicco.dev.exception.EntityNotFoundException;
import fr.cybercicco.dev.repository.UtilisateurRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UtilisateurRepository utilisateurRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        Utilisateur user = Utilisateur.builder()
                .prenom(request.getPrenom())
                .nom(request.getNom())
                .mdp(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .isAdmin(false)
                .allergenes(new ArrayList<>(0))
                .build();
        utilisateurRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Utilisateur user = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new EntityNotFoundException("L'utilisateur n'a pas été trouvé en base"));
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
