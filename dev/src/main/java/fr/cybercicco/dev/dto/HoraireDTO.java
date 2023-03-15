package fr.cybercicco.dev.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalTime;

@Validated
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HoraireDTO {

    @NotBlank
    @Pattern(regexp = "(lundi)|(mardi)|(mercredi)|(jeudi)|(vendredi)|(samedi)|(dimanche)")
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
}
