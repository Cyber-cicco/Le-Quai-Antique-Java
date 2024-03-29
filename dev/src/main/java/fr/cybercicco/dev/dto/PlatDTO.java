package fr.cybercicco.dev.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
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
public class PlatDTO {

    @NotNull
    private Integer id;
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

    private List<String> allergenes;

}
