package fr.cybercicco.dev.service;

import fr.cybercicco.dev.dto.PlatDTO;
import fr.cybercicco.dev.dto.PlatMapper;
import fr.cybercicco.dev.entity.Plat;
import fr.cybercicco.dev.exception.DuplicateEntryException;
import fr.cybercicco.dev.repository.AllergeneRepository;
import fr.cybercicco.dev.repository.PlatRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Validated
@Slf4j
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

    @Transactional
    public String uploadPhoto(Integer id, MultipartFile file) throws IOException {
        Plat plat = platRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        plat.setPhoto(file.getOriginalFilename());
        file.transferTo(new File("/home/hijokaidan/Documents/Studi/Projets/ECF/Le-Quai-Antique-Java/dev/images/"+file.getOriginalFilename()));
        platRepository.save(plat);
        return plat.getPhoto();
    }
}
