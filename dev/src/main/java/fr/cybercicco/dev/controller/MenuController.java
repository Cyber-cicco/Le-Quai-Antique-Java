package fr.cybercicco.dev.controller;

import fr.cybercicco.dev.dto.FormuleDTO;
import fr.cybercicco.dev.dto.MenuDTOGet;
import fr.cybercicco.dev.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menus/")
@RequiredArgsConstructor
@CrossOrigin
public class MenuController {

    private final MenuService menuService;

    @GetMapping("list")
    public ResponseEntity<List<MenuDTOGet>> getMenus(){
        return ResponseEntity.ok(menuService.listAll());
    }

    @GetMapping("list_nom")
    public ResponseEntity<List<String>>getNomMenus(){
        return ResponseEntity.ok(menuService.getNomsMenus());
    }

    @GetMapping("formules")
    public ResponseEntity<List<FormuleDTO>> getFormules() {
        return ResponseEntity.ok(menuService.listFormules());
    }
}
