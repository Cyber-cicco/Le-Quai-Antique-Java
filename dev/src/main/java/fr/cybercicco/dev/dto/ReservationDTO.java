package fr.cybercicco.dev.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
@Validated
public class ReservationDTO {


    @NotNull
    private LocalDateTime dateReservation;

    private List<String> allergenes;

    @NotNull
    private Integer nbPlaces;

    private String restaurant;

    private boolean soir;

}
