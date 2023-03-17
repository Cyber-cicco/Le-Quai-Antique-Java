package fr.cybercicco.dev.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTOPost {

    @NotNull
    Integer id;
    @NotBlank
    @Length(min = 2, max = 255)
    private String nomMenu;

    @NotBlank
    @Length(min = 12, max = 2000)
    private String description;

    private List<String> formules;
}
