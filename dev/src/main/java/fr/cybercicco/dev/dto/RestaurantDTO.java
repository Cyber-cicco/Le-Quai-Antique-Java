package fr.cybercicco.dev.dto;

import fr.cybercicco.dev.entity.Horaire;
import fr.cybercicco.dev.entity.Place;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;

@Validated
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RestaurantDTO {

    @NotBlank
    @Length(min = 2, max = 255)
    private String nomRestaurant;
    @NotBlank
    @Length(min = 12, max = 2000)
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @NotNull
    private BigDecimal maxConvivesAutorises;

}
