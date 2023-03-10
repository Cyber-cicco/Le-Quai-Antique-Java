package fr.cybercicco.dev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Horaire {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Pattern(regexp = "(lundi)|(mardi)|(mercredi)|(jeudi)|(vendredi)|(samedi)|(dimanche)")
    private String jourSemaine;
    @NotNull
    private LocalTime ouvertureDejeuner;
    @NotNull
    private LocalTime fermetureDejeuner;
    @NotNull
    private LocalTime ouvertureDiner;
    @NotNull
    private LocalTime fermetureDiner;

    @ManyToMany
    @JoinTable(name="restaurant_horaires",
            joinColumns = @JoinColumn(name = "horaire_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    )
    private List<Restaurant> restaurants;
}
