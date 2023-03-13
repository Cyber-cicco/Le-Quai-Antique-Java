package fr.cybercicco.dev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Allergene {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(unique = true)
    private String nomAllergene;

    @ManyToMany
    @JoinTable(name="allergie",
            joinColumns = @JoinColumn(name = "allergene_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "utilisateur_id", referencedColumnName = "id")
    )
    private List<Utilisateur> utilisateurs;
    @ManyToMany
    @JoinTable(name="plat_allergene",
            joinColumns = @JoinColumn(name = "allergene_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "plat_id", referencedColumnName = "id")
    )
    private List<Plat> plats;

    @ManyToMany
    @JoinTable(name="reservation_allergie",
            joinColumns = @JoinColumn(name = "allergene_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    )
    private List<Reservation> reservations;

}
