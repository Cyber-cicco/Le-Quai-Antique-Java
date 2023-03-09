package fr.cybercicco.dev.dto;

import fr.cybercicco.dev.entity.Allergene;
import fr.cybercicco.dev.entity.Plat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlatMapper {

    default PlatDTO toPlatDto(Plat plat){
        return PlatDTO.builder()
                .nomPlat(plat.getNomPlat())
                .typePlat(plat.getTypePlat())
                .photo(plat.getPhoto())
                .prix(plat.getPrix())
                .description(plat.getDescription())
                .allergenes(plat.getAllergenes().stream().map(Allergene::getNomAllergene).toList())
                .build();
    };
}
