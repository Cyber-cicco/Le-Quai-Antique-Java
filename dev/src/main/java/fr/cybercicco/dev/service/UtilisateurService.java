package fr.cybercicco.dev.service;

import fr.cybercicco.dev.controller.message.AuthenticationResponse;
import fr.cybercicco.dev.dto.UtilisateurMapper;
import fr.cybercicco.dev.dto.UtilisateurDTO;
import fr.cybercicco.dev.entity.Utilisateur;
import fr.cybercicco.dev.exception.DuplicateEntryException;
import fr.cybercicco.dev.repository.AllergeneRepository;
import fr.cybercicco.dev.repository.UtilisateurRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Validated
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    private final UtilisateurMapper utilisateurMapper;

    private final JwtService jwtService;

    private final AllergeneRepository allergeneRepository;

    public UtilisateurDTO getUtilisateurByEmail(String email){
        return utilisateurMapper.toUtilisateurDTO(utilisateurRepository.findByEmail(email).orElseThrow(EntityExistsException::new));
    }

    @Transactional
    public AuthenticationResponse updateUtilisateur(String authorization, @Valid UtilisateurDTO utilisateurDTO) {
        String currentMail = jwtService.extractEmail(authorization.substring(7));
        if(!Objects.equals(currentMail, utilisateurDTO.getEmail()) && utilisateurRepository.existsByEmail(utilisateurDTO.getEmail())){
            throw new DuplicateEntryException("L'email existe déjà");
        }
        Utilisateur utilisateur =  utilisateurRepository.findByEmail(currentMail)
                .orElseThrow(EntityNotFoundException::new);
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.getAllergenes().clear();
        utilisateurDTO.getAllergenes()
                .forEach(e->{utilisateur.getAllergenes().add(allergeneRepository.findByNomAllergene(e).
                        orElseThrow(EntityNotFoundException::new));});
        utilisateur.setNbConvives(utilisateurDTO.getNbConvives());
        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setPrenom(utilisateurDTO.getPrenom());
        utilisateurRepository.save(utilisateur);
        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(utilisateur))
                .build();
    }
}
