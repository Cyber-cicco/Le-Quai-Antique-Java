package fr.cybercicco.dev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Utilisateur {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @Email
    private String email;

    @NotNull
    private Boolean isAdmin;

    private BigDecimal nbConvives;

    @OneToMany(mappedBy = "utilisateur")
    private List<Reservation> reservations;

    @ManyToMany
    @JoinTable(name="allergie",
            joinColumns = @JoinColumn(name = "utilisateur_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "allergene_id", referencedColumnName = "id")
    )
    private List<Allergene> allergenes;


}
