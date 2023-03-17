package fr.cybercicco.dev.dto;

import fr.cybercicco.dev.entity.Horaire;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HoraireMapper {
    HoraireDTO toHoraireDTO(Horaire horaire);

}
