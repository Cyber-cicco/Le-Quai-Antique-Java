package fr.cybercicco.dev.controller;

import fr.cybercicco.dev.dto.PlatDTO;
import fr.cybercicco.dev.service.PlatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/plats/")
@RequiredArgsConstructor
@CrossOrigin
public class PlatController {

    private final PlatService platService;

    @GetMapping("list")
    public ResponseEntity<List<PlatDTO>> getPlats(){
        return ResponseEntity.ok(platService.getAllPlats());
    }
}
