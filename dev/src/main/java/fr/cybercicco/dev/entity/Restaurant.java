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
public class Restaurant {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Length(min = 2, max = 255)
    private String nomRestaurant;
    @ManyToMany
    @JoinTable(name="restaurant_horaires",
            joinColumns = @JoinColumn(name = "restaurant_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "horaire_id", referencedColumnName = "id")
    )
    private List<Horaire> horaires;

    @OneToMany(mappedBy = "restaurant")
    private List<Place> tables;
}
