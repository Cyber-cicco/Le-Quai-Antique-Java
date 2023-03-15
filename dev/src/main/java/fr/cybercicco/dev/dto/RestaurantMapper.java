package fr.cybercicco.dev.dto;

import fr.cybercicco.dev.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    RestaurantDTO toRestaurantDTO(Restaurant restaurant);
}
