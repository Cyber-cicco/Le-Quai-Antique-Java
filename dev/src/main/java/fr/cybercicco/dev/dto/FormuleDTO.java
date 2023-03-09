package fr.cybercicco.dev.dto;

import fr.cybercicco.dev.entity.Plat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    @NotBlank
    @Length(max = 255)
    private String nomFormule;

    @NotBlank
    @Length(min = 12, max = 1000)
    private String description;
    @NotNull
    private BigDecimal prix;
    private List<PlatDTO> plats;
}
