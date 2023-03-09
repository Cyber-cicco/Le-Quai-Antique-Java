package fr.cybercicco.dev.dto;

import fr.cybercicco.dev.entity.Formule;
import fr.cybercicco.dev.entity.Menu;
import fr.cybercicco.dev.entity.Plat;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    default MenuDTO toMenuDTO(Menu menu, FormuleMapper formuleMapper, PlatMapper platMapper){
        return MenuDTO.builder()
                .nomMenu(menu.getNomMenu())
                .description(menu.getDescription())
                .formules(menu.getFormules().stream().map(formule -> formuleMapper.toFormuleDTO(formule, platMapper)).toList())
                .build();
    }
}
