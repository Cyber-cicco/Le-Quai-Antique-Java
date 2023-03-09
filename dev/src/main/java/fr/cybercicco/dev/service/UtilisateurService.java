package fr.cybercicco.dev.service;

import fr.cybercicco.dev.dto.UtilisateurMapper;
import fr.cybercicco.dev.dto.UtilisateurDTO;
import fr.cybercicco.dev.repository.UtilisateurRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    private final UtilisateurMapper utilisateurMapper;
    public UtilisateurDTO getUtilisateurByEmail(String email){
        return utilisateurMapper.toUtilisateurDTO(utilisateurRepository.findByEmail(email).orElseThrow(EntityExistsException::new));
    }
}
