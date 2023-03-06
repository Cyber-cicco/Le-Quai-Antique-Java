package fr.cybercicco.dev.dto;

import fr.cybercicco.dev.entity.Plat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlatMapper {

    PlatDTO toPlatDto(Plat plat);
}
