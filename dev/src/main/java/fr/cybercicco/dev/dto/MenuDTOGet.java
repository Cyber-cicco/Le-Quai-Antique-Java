package fr.cybercicco.dev.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class MenuDTOGet {

    @NotNull
    Integer id;

    @NotBlank
    @Length(min = 2, max = 255)
    private String nomMenu;

    @NotBlank
    @Length(min = 12, max = 2000)
    private String description;

    private List<FormuleDTO> formules;

}
