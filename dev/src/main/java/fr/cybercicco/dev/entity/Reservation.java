package fr.cybercicco.dev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Reservation {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private LocalDateTime dateReservation;

    private String nom;

    private String prenom;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "table_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToMany
    @JoinTable(name="reservation_allergie",
            joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "allergene_id", referencedColumnName = "id")
    )
    private List<Allergene> allergenes;


}
