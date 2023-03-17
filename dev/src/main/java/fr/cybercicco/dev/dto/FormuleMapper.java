package fr.cybercicco.dev.dto;

import fr.cybercicco.dev.entity.Formule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FormuleMapper {

    default FormuleDTO toFormuleDTO(Formule formule, PlatMapper platMapper){
        return FormuleDTO.builder()
                .id(formule.getId())
                .nomFormule(formule.getNomFormule())
                .description(formule.getDescription())
                .plats(formule.getPlats().stream().map(platMapper::toPlatDto).toList())
                .prix(formule.getPrix())
                .menu((formule.getMenu() != null) ? formule.getMenu().getNomMenu() : null)
                .build();
    }

}
