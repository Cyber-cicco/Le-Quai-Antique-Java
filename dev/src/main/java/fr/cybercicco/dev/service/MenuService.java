package fr.cybercicco.dev.service;

import fr.cybercicco.dev.dto.FormuleMapper;
import fr.cybercicco.dev.dto.MenuDTO;
import fr.cybercicco.dev.dto.MenuMapper;
import fr.cybercicco.dev.dto.PlatMapper;
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

    private final PlatMapper platMapper;

    private final FormuleMapper formuleMapper;

    private final MenuRepository menuRepository;

    public List<MenuDTO> listAll() {
        return menuRepository.findAll().stream().map(menu -> menuMapper.toMenuDTO(menu, formuleMapper, platMapper)).toList();
    }
}
