package fr.cybercicco.dev.service;

import fr.cybercicco.dev.dto.*;
import fr.cybercicco.dev.repository.FormuleRepository;
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

    private final FormuleRepository formuleRepository;

    public List<MenuDTOGet> listAll() {
        return menuRepository.findAll().stream().map(menu -> menuMapper.toMenuDTOGet(menu, formuleMapper, platMapper)).toList();
    }

    public List<FormuleDTO> listFormules() {
        return formuleRepository.findAll().stream().map(formule -> formuleMapper.toFormuleDTO(formule, platMapper)).toList();
    }

    public String changeOneMenu(MenuDTOPost menuDTO) {
        return null;
    }
}
