package fr.cybercicco.dev.service;

import fr.cybercicco.dev.dto.*;
import fr.cybercicco.dev.entity.Formule;
import fr.cybercicco.dev.entity.Menu;
import fr.cybercicco.dev.exception.DuplicateEntryException;
import fr.cybercicco.dev.repository.FormuleRepository;
import fr.cybercicco.dev.repository.MenuRepository;
import fr.cybercicco.dev.repository.PlatRepository;
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

    private final PlatRepository platRepository;

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

    public List<String> getNomsMenus() {
        return menuRepository.findAll().stream().map(Menu::getNomMenu).toList();
    }

    @Transactional
    public FormuleDTO changeOneFormule(FormuleDTO formuleDTO) {
        Formule formule = formuleRepository.findById(formuleDTO.getId()).orElseThrow(EntityNotFoundException::new);
        if(formuleRepository.existsByNomFormule(formuleDTO.getNomFormule()) && !Objects.equals(formuleDTO.getNomFormule(), formule.getNomFormule())){
            throw new DuplicateEntryException("Le nom de la formule doit être unique");
        }
        formule.setNomFormule(formuleDTO.getNomFormule());
        formule.setDescription(formule.getDescription());
        formule.setPrix(formuleDTO.getPrix());
        formule.setMenu(menuRepository.findByNomMenu(formuleDTO.getMenu()).orElseThrow(EntityNotFoundException::new));
        formule.getPlats().clear();
        formuleDTO.getPlats().forEach(plat-> formule.getPlats().add(platRepository.findByNomPlat(plat.getNomPlat()).orElseThrow(EntityNotFoundException::new)));
        formuleRepository.save(formule);
        return formuleMapper.toFormuleDTO(formule, platMapper);
    }
}
