package fr.cybercicco.dev.service;

import fr.cybercicco.dev.controller.message.ReservationResponse;
import fr.cybercicco.dev.dto.ReservationDTO;
import fr.cybercicco.dev.entity.Horaire;
import fr.cybercicco.dev.entity.Place;
import fr.cybercicco.dev.entity.Reservation;
import fr.cybercicco.dev.entity.Restaurant;
import fr.cybercicco.dev.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final HoraireRepository horaireRepository;

    private final PlaceRepository placeRepository;

    private final AllergeneRepository allergeneRepository;

    private final JwtService jwtService;

    private final UtilisateurRepository utilisateurRepository;

    private final RestaurantRepository restaurantRepository;

    private String[] weekdays = {"LUNDI","MARDI","MERCREDI", "JEUDI", "VENDREDI", "SAMEDI", "DIMANCHE"};

    public ReservationResponse getReservationsForCurrentDay(String date, boolean soir, String restaurant) {
        LocalDate ldDebut = (date.charAt(4)!='-')? LocalDate.now() : LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String weekDay = weekdays[ldDebut.getDayOfWeek().getValue()-1];
        List<Horaire> horaireForWeekDayList = horaireRepository.getHoraireForCurrentDay(weekDay, restaurant);
        if(horaireForWeekDayList.isEmpty()) return new ReservationResponse(0);
        Horaire horaireForWeekDay = horaireForWeekDayList.get(0);
        LocalDateTime ldtDebut = ldDebut.atTime((soir)? horaireForWeekDay.getOuvertureDiner() : horaireForWeekDay.getOuvertureDejeuner());
        LocalDateTime ldtFin = ldDebut.atTime((soir)? horaireForWeekDay.getFermetureDiner() : horaireForWeekDay.getFermetureDejeuner());
        Integer nbPlaceReservees = reservationRepository.getNumberOfTakenPlaces(restaurant, ldtDebut, ldtFin );
        if(nbPlaceReservees == null) nbPlaceReservees = 0;
        Integer nbPlacesTotale = placeRepository.countAllPlaces(restaurant);
        return new ReservationResponse(nbPlacesTotale-nbPlaceReservees);
    }

    @Transactional
    public ReservationResponse saveReservations(ReservationDTO reservationDTO, String authorization) {
        String email = jwtService.extractEmail(authorization.substring(7));
        Restaurant restaurant = restaurantRepository.findByNomRestaurant(reservationDTO.getRestaurant()).orElseThrow(EntityNotFoundException::new);
        List<Place> places = placeRepository.findByRestaurant(restaurant);

        return null;
    }

    public List<Place> findBestPlaceToReserve(List<Place> places, Integer nbPlaces){
        return null;
    }

    @Transactional
    public void saveOneReservation(ReservationDTO reservationDTO, Place place, String email){
        Reservation reservation = Reservation.builder()
                .dateReservation(reservationDTO.getDateReservation())
                .allergenes(reservationDTO.getAllergenes().stream()
                        .map(val-> allergeneRepository.findByNomAllergene(val).orElseThrow(EntityNotFoundException::new))
                        .toList())
                .utilisateur((email == null) ? null : utilisateurRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new))
                .place(place)
                .build();
    }
}
