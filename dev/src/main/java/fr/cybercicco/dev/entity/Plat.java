package fr.cybercicco.dev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Plat {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @Length(min = 2, max = 255)
    @NotBlank
    private String nomPlat;

    @NotBlank
    @Length(min = 12, max = 255)
    private String description;

    @NotBlank
    @Pattern(regexp = "(entree)|(plat)|(dessert)")
    private String typePlat;
}
