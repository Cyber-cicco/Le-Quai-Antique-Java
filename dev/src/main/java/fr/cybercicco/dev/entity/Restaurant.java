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
public class Restaurant {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Length(min = 2, max = 255)
    private String nomRestaurant;
    @OneToMany(mappedBy = "restaurant")
    private List<Horaire> horaires;
    @NotBlank
    @Length(min = 12, max = 2000)
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @NotNull
    private BigDecimal maxConvivesAutorises;

    @OneToMany(mappedBy = "restaurant")
    private List<Place> tables;
}
