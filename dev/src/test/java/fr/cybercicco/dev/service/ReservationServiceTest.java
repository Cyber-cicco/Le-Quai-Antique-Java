package fr.cybercicco.dev.service;

import fr.cybercicco.dev.entity.Place;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @InjectMocks
    ReservationService reservationService;
    @Test
    void findBestPlaceToReserve() {
        Place place1 = Mockito.mock(Place.class);
        Place place2 = Mockito.mock(Place.class);
        Place place3 = Mockito.mock(Place.class);
        Place place4 = Mockito.mock(Place.class);
        Place place5 = Mockito.mock(Place.class);
        Mockito.when(place1.getNbPlaces()).thenReturn(new BigDecimal("4"));
        Mockito.when(place2.getNbPlaces()).thenReturn(new BigDecimal("4"));
        Mockito.when(place3.getNbPlaces()).thenReturn(new BigDecimal("8"));
        Mockito.when(place4.getNbPlaces()).thenReturn(new BigDecimal("12"));
        Mockito.when(place5.getNbPlaces()).thenReturn(new BigDecimal("6"));

        List<Place> places = List.of(place1, place2, place3, place4, place5);

        assertArrayEquals(reservationService.findBestPlaceToReserve(places, 5).toArray(), List.of(place5).toArray());
        assertArrayEquals(reservationService.findBestPlaceToReserve(places, 7).toArray(), List.of(place3).toArray());
        assertArrayEquals(reservationService.findBestPlaceToReserve(places, 9).toArray(), List.of(place5, place2).toArray());
    }
}