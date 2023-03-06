package fr.cybercicco.dev.service;

import fr.cybercicco.dev.dto.PlatDTO;
import fr.cybercicco.dev.dto.PlatMapper;
import fr.cybercicco.dev.repository.PlatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class PlatService {

    private final PlatRepository platRepository;
    private final PlatMapper platMapper;

    public List<PlatDTO> getAllPlats() {
        return platRepository.findAll().stream().map(platMapper::toPlatDto).toList();
    }
}
