package fr.cybercicco.dev.dto;

import fr.cybercicco.dev.entity.Menu;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    default MenuDTOGet toMenuDTOGet(Menu menu, FormuleMapper formuleMapper, PlatMapper platMapper){
        return MenuDTOGet.builder()
                .nomMenu(menu.getNomMenu())
                .description(menu.getDescription())
                .formules(menu.getFormules().stream().map(formule -> formuleMapper.toFormuleDTO(formule, platMapper)).toList())
                .build();
    }

}
