package fr.cybercicco.dev.service;

import fr.cybercicco.dev.controller.message.ReservationResponse;
import fr.cybercicco.dev.dto.ReservationDTO;
import fr.cybercicco.dev.entity.Horaire;
import fr.cybercicco.dev.entity.Place;
import fr.cybercicco.dev.entity.Reservation;
import fr.cybercicco.dev.exception.UndefinedHorairesForWeekDayException;
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
import java.util.*;

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

    private String[] weekdays = {"LUNDI","MARDI","MERCREDI", "JEUDI", "VENDREDI", "SAMEDI", "DIMANCHE"};

    public ReservationResponse getReservationsForCurrentDay(String date, boolean soir, String restaurant) {
        LocalDate ldDebut = (date.charAt(4)!='-')? LocalDate.now() : LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return getReservationFromCurrentDate(ldDebut, soir, restaurant);
    }

    private ReservationResponse getReservationFromCurrentDate(LocalDate ldDebut, boolean soir, String restaurant){
        LocalDateTime[] ldts =  getHoraireRangeInCurrentDate(ldDebut, soir, restaurant);
        Integer nbPlaceReservees = reservationRepository.getNumberOfTakenPlaces(restaurant, ldts[0], ldts[1]);
        if(nbPlaceReservees == null) nbPlaceReservees = 0;
        Integer nbPlacesTotale = placeRepository.countAllPlaces(restaurant);
        return new ReservationResponse(nbPlacesTotale-nbPlaceReservees);
    }

    private LocalDateTime[] getHoraireRangeInCurrentDate(LocalDate currentDate, boolean soir, String restaurant){
        String weekDay = weekdays[currentDate.getDayOfWeek().getValue()-1];
        List<Horaire> horaireForWeekDayList = horaireRepository.getHoraireForCurrentDay(weekDay, restaurant);
        if(horaireForWeekDayList.isEmpty()) throw new UndefinedHorairesForWeekDayException();
        Horaire horaireForWeekDay = horaireForWeekDayList.get(0);
        LocalDateTime ldtDebut = currentDate.atTime((soir)? horaireForWeekDay.getOuvertureDiner() : horaireForWeekDay.getOuvertureDejeuner());
        LocalDateTime ldtFin = currentDate.atTime((soir)? horaireForWeekDay.getFermetureDiner() : horaireForWeekDay.getFermetureDejeuner());
        return new LocalDateTime[]{ldtDebut, ldtFin};
    }

    @Transactional
    public ReservationResponse saveReservations(ReservationDTO reservationDTO, String authorization) {
        String email = authorization!=null? jwtService.extractEmail(authorization.substring(7)):null;
        LocalDateTime[] rangeForCurrentDate = getHoraireRangeInCurrentDate(reservationDTO.getDateReservation().toLocalDate(), reservationDTO.isSoir(), reservationDTO.getRestaurant());
        List<Place> places = placeRepository.findAllFreePlaces(rangeForCurrentDate[0], rangeForCurrentDate[1], reservationDTO.getRestaurant());
        List<Place> chosenPlaces = findBestPlaceToReserve(places, reservationDTO.getNbPlaces());
        chosenPlaces.forEach((var)-> saveOneReservation(reservationDTO, var, email));
        return getReservationFromCurrentDate(reservationDTO.getDateReservation().toLocalDate(), reservationDTO.isSoir(), reservationDTO.getRestaurant());
    }

    /**
     * L'utilité et le fonctionnement de cette méthode est absolument évident, inutile donc de commenter
     * Blague à part:
     * Parmis la liste des tables non réservées, va essayer de déterminer toutes les combinaisons de tables
     * pouvant contenir le nombre de personnes souhaitées.
     * Parmis ces combinaisons, il va retenir la dernière combinaison de tables permettant de contenir au mieux
     * ces personnes sans gacher de place, et va renvoyer la liste des tables qu'il faut donc réserver compte tenu
     * du nombre de personnes à réserver.
     * Cet algorithme fonctionne de la façon suivante :
     * La magie noire.
     * Cette méthode est donc dédiées à Bibine, la chèvre sacrifiée pour la mettre au point.
     */
    public List<Place> findBestPlaceToReserve(List<Place> places, Integer nbPlaces){
        Map<Integer, List<Place>> sizeToPlaces = new HashMap<>();
        for(int i = 0; i < places.size(); i++){
            if(places.get(i).getNbPlaces().intValueExact() < nbPlaces+1){
                for(int j = 0; j < places.size(); j++){
                    if(i != j){
                        List<Place> placesForOneRow = new ArrayList<>();
                        placesForOneRow.add(places.get(i));
                        int k = 0;
                        while(placesForOneRow.stream().map(val-> val.getNbPlaces().intValueExact()).mapToInt(Integer::intValue).sum() < nbPlaces+1 && j+k < places.size()){
                            placesForOneRow.add(places.get(j+k));
                            k++;
                        }
                        sizeToPlaces.put(placesForOneRow.stream().map(val-> val.getNbPlaces().intValueExact()).mapToInt(Integer::intValue).sum(), placesForOneRow);
                    }
                }
            } else {
                sizeToPlaces.put(places.get(i).getNbPlaces().intValueExact(), List.of(places.get(i)));
            }
        }
        SortedSet<Integer> keys = new TreeSet<>(sizeToPlaces.keySet());
        for(Integer key : keys){
            if(key > nbPlaces){
                return sizeToPlaces.get(key);
            }
        }
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
        reservationRepository.save(reservation);
    }
}
