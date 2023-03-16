package fr.cybercicco.dev.service;

import fr.cybercicco.dev.dto.*;
import fr.cybercicco.dev.entity.Menu;
import fr.cybercicco.dev.exception.DuplicateEntryException;
import fr.cybercicco.dev.repository.FormuleRepository;
import fr.cybercicco.dev.repository.MenuRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

@Service
@Validated
@RequiredArgsConstructor
@Slf4j
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

    @Transactional
    public MenuDTOGet changeOneMenu(@Valid MenuDTOPost menuDTO) {
        Menu menu = menuRepository.findById(menuDTO.getId()).orElseThrow(EntityNotFoundException::new);
        if(menuRepository.existsByNomMenu(menuDTO.getNomMenu()) && !Objects.equals(menuDTO.getNomMenu(), menu.getNomMenu())){
            throw new DuplicateEntryException("Le nom de menu doit être unique");
        }

        menu.setNomMenu(menuDTO.getNomMenu());
        menu.setDescription(menuDTO.getDescription());
        menuRepository.save(menu);
        return menuMapper.toMenuDTOGet(menu, formuleMapper, platMapper);
    }
}
