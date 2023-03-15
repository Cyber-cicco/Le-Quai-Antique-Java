package fr.cybercicco.dev.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

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
