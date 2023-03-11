package fr.cybercicco.dev.controller.message;

import fr.cybercicco.dev.dto.HoraireDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {

    private Integer nbPlacesRestantes;

}
