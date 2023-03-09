package fr.cybercicco.dev.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@AllArgsConstructor
@Data
@Builder
public class MenuDTO {

    @NotBlank
    @Length(min = 2, max = 255)
    @Column(unique = true)
    private String nomMenu;

    @NotBlank
    @Length(min = 12, max = 2000)
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private List<String> formules;

    private List<String> photos;
}
