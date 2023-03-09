package fr.cybercicco.dev.service;

import fr.cybercicco.dev.dto.MenuDTO;
import fr.cybercicco.dev.dto.MenuMapper;
import fr.cybercicco.dev.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class MenuService {

    private final MenuMapper menuMapper;

    private final MenuRepository menuRepository;
    public List<MenuDTO> listAll() {
        return menuRepository.findAll().stream().map(menuMapper::toMenuDTO).toList();
    }
}
