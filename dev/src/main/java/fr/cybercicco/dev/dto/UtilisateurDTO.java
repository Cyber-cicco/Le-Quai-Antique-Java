package fr.cybercicco.dev.dto;

import fr.cybercicco.dev.controller.message.AuthenticationRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class UtilisateurDTO {
    @Email
    private String email;

    @NotBlank
    @Length(min = 1, max = 255)
    private String prenom;

    @NotBlank
    @Length(min = 1, max = 255)
    private String nom;

    private Boolean isAdmin;

    private BigDecimal nbConvives;

    @OneToMany(mappedBy = "utilisateur")
    private List<String> reservations;

    private List<String> allergenes;

}
