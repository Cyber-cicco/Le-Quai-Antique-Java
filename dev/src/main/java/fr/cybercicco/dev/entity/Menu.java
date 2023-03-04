package fr.cybercicco.dev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Menu {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Length(min = 2, max = 255)
    @Column(unique = true)
    private String nomMenu;

    @NotBlank
    @Length(min = 12, max = 2000)
    private String description;

    @OneToMany(mappedBy = "menu")
    private List<Formule> formules;
}
