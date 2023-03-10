package fr.cybercicco.dev.dto;

import fr.cybercicco.dev.entity.Allergene;
import fr.cybercicco.dev.entity.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "allergenes", ignore =true)
    UtilisateurDTO basicFieldsMapper(Utilisateur utilisateur);

    default UtilisateurDTO toUtilisateurDTO(Utilisateur utilisateur){
        UtilisateurDTO utilisateurDTO = basicFieldsMapper(utilisateur);
        utilisateurDTO.setAllergenes(utilisateur.getAllergenes().stream().map(Allergene::getNomAllergene).toList());
        //TODO : implement reservations when the time will come
        return utilisateurDTO;
    }
}
