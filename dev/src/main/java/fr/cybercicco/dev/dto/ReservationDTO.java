package fr.cybercicco.dev.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Validated
public class ReservationDTO {


    @NotNull
    private LocalDateTime dateReservation;

}
