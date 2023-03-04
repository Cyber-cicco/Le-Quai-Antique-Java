package fr.cybercicco.dev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Formule {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Length(max = 255)
    @Column(unique = true)
    private String nomFormule;

    @NotBlank
    @Length(min = 12, max = 1000)
    @Column(columnDefinition = "LONGTEXT")
    private String Description;
    @NotNull
    private BigDecimal prix;
    @ManyToMany
    @JoinTable(name="composition_formule",
            joinColumns = @JoinColumn(name = "formule_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "plat_id", referencedColumnName = "id")
    )
    private List<Plat> plats;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

}
