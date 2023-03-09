package fr.cybercicco.dev.controller;

import fr.cybercicco.dev.dto.MenuDTO;
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
    public ResponseEntity<List<MenuDTO>> getMenus(){
        return ResponseEntity.ok(menuService.listAll());
    }
}
