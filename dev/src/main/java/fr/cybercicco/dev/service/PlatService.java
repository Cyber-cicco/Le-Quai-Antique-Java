package fr.cybercicco.dev.service;

import fr.cybercicco.dev.dto.PlatDTO;
import fr.cybercicco.dev.dto.PlatMapper;
import fr.cybercicco.dev.entity.Plat;
import fr.cybercicco.dev.exception.DuplicateEntryException;
import fr.cybercicco.dev.repository.AllergeneRepository;
import fr.cybercicco.dev.repository.PlatRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Validated
public class PlatService {

    private final PlatRepository platRepository;

    private final AllergeneRepository allergeneRepository;

    private final PlatMapper platMapper;

    public List<PlatDTO> getAllPlats() {
        return platRepository.findAll().stream().map(platMapper::toPlatDto).toList();
    }

    public PlatDTO changeOnePlat(PlatDTO platDTO) {
        Plat plat = platRepository.findById(platDTO.getId()).orElseThrow(EntityNotFoundException::new);
        if(platRepository.existsByNomPlat(platDTO.getNomPlat()) && !Objects.equals(platDTO.getNomPlat(), plat.getNomPlat())){
            throw new DuplicateEntryException("Le nom de la formule doit être unique");
        }
        plat.setNomPlat(platDTO.getNomPlat());
        plat.setTypePlat(platDTO.getTypePlat());
        plat.setPrix(platDTO.getPrix());
        plat.setDescription(platDTO.getDescription());
        plat.getAllergenes().clear();
        platDTO.getAllergenes().forEach(val -> plat.getAllergenes().add(allergeneRepository.findByNomAllergene(val).orElseThrow(EntityNotFoundException::new)));
        platRepository.save(plat);
        return platMapper.toPlatDto(plat);
    }

    public Object changePhoto(String photo){
        return null;
    }
}
