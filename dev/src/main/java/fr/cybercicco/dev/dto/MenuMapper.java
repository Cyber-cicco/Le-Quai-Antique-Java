package fr.cybercicco.dev.dto;

import fr.cybercicco.dev.entity.Formule;
import fr.cybercicco.dev.entity.Menu;
import fr.cybercicco.dev.entity.Plat;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    default MenuDTO toMenuDTO(Menu menu){
        return MenuDTO.builder()
                .nomMenu(menu.getNomMenu())
                .description(menu.getDescription())
                .formules(menu.getFormules().stream().map(Formule::getNomFormule).toList())
                .photos(menu.getFormules().stream()
                        .map(formule -> formule.getPlats().stream()
                                .map(Plat::getPhoto).toList())
                        .flatMap(Collection::stream)
                        .distinct()
                        .collect(Collectors.toList()))
                .build();
    }
}
