package fr.cybercicco.dev.service;

import fr.cybercicco.dev.controller.message.ReservationResponse;
import fr.cybercicco.dev.dto.HoraireMapper;
import fr.cybercicco.dev.dto.ReservationDTO;
import fr.cybercicco.dev.entity.Horaire;
import fr.cybercicco.dev.repository.HoraireRepository;
import fr.cybercicco.dev.repository.PlaceRepository;
import fr.cybercicco.dev.repository.ReservationRepository;
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

    private final JwtService jwtService;

    private String[] weekdays = {"LUNDI","MARDI","MERCREDI", "JEUDI", "VENDREDI", "SAMEDI", "DIMANCHE"};

    public ReservationResponse getReservationsForCurrentDay(String date, boolean soir, String restaurant) {
        LocalDate ldDebut = (date.charAt(4)!='-')? LocalDate.now() : LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String weekDay = weekdays[ldDebut.getDayOfWeek().getValue()-1];
        List<Horaire> horaireForWeekDayList = horaireRepository.getHoraireForCurrentDay(weekDay, restaurant);
        if(horaireForWeekDayList.isEmpty()) return new ReservationResponse(0);
        Horaire horaireForWeekDay = horaireForWeekDayList.get(0);
        LocalDateTime ldtDebut = ldDebut.atTime((soir)? horaireForWeekDay.getOuvertureDiner() : horaireForWeekDay.getOuvertureDejeuner());
        LocalDateTime ldtFin = ldDebut.atTime((soir)? horaireForWeekDay.getFermetureDiner() : horaireForWeekDay.getFermetureDejeuner());
        Integer nbPlaceReservees = reservationRepository.countReservationByDateReservationBetween(ldtDebut, ldtFin);
        Integer nbPlacesTotale = placeRepository.countAllPlaces(restaurant);
        return new ReservationResponse(nbPlacesTotale-nbPlaceReservees);
    }

    public ReservationResponse saveReservation(ReservationDTO reservationDTO, String authorization) {
        String email = jwtService.extractEmail(authorization.substring(7));

        return null;
    }
}
