package fr.cybercicco.dev.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Validated
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlatDTO {

    @Column(unique = true)
    @Length(min = 2, max = 255)
    @NotBlank
    private String nomPlat;

    @NotBlank
    @Length(min = 12, max = 2000)
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @NotBlank
    @Pattern(regexp = "(ENTREE)|(PRINCIPAL)|(DESSERT)")
    private String typePlat;

    @Min(2)
    @Max(1000)
    private BigDecimal prix;

    @NotBlank
    private String photo;

}
