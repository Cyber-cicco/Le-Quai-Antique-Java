package fr.cybercicco.dev.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class FormuleDTO {

    @NotNull
    private Integer id;
    @NotBlank
    @Length(max = 255)
    private String nomFormule;

    @NotBlank
    @Length(min = 12, max = 1000)
    private String description;
    @NotNull
    private BigDecimal prix;
    private List<PlatDTO> plats;
    private String menu;
}
