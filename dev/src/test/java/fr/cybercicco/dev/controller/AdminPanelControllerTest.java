package fr.cybercicco.dev.controller;

import fr.cybercicco.dev.dto.MenuDTOGet;
import fr.cybercicco.dev.dto.MenuDTOPost;
import fr.cybercicco.dev.repository.MenuRepository;
import fr.cybercicco.dev.service.MenuService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminPanelControllerTest {

    @Mock
    private MenuService menuService;

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private AdminPanelController adminPanelController;

    @Test
    void getAdminDashboardTest() {
        // Set up expected response
        Map<String, String> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "tu es authentifié admin");

        // Call method under test
        ResponseEntity<Map<String, String>> responseEntity = adminPanelController.getAdminDashboard();

        // Verify response status code and body
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }


    @Test
    void changeMenuTest() {
        MenuDTOPost menuDTOPost = MenuDTOPost.builder().nomMenu("menu1").build();
        when(menuService.changeOneMenu(menuDTOPost)).thenReturn(MenuDTOGet.builder().nomMenu("menu2").build());

        ResponseEntity<Map<String, Object>> responseEntity = adminPanelController.changeMenu(menuDTOPost);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Menu bien inséré en base", Objects.requireNonNull(responseEntity.getBody()).get("message"));
        verify(menuService, times(1)).changeOneMenu(menuDTOPost);
    }

    @Test
    void checkIfNomMenuExistsTest() {
        String nom = "menu name";
        when(menuRepository.existsByNomMenu(nom)).thenReturn(true);

        ResponseEntity<Map<String, Boolean>> responseEntity = adminPanelController.checkIfNomMenuExists(nom);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(true, Objects.requireNonNull(responseEntity.getBody()).get("exists"));
        verify(menuRepository, times(1)).existsByNomMenu(nom);
    }
}
