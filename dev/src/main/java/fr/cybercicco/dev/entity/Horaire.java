package fr.cybercicco.dev.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Horaire {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Pattern(regexp = "(LUNDI)|(MARDI)|(MERCREDI)|(JEUDI)|(VENDREDI)|(SAMEDI)|(DIMANCHE)")
    private String jourSemaine;
    @NotNull
    private LocalTime ouvertureDejeuner;
    @NotNull
    private LocalTime fermetureDejeuner;
    @NotNull
    private Boolean ouvertDejeuner;
    @NotNull
    private Boolean ouvertDiner;
    @NotNull
    private LocalTime ouvertureDiner;
    @NotNull
    private LocalTime fermetureDiner;

    @ManyToOne
    private Restaurant restaurant;
}
