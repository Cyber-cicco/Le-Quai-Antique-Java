package fr.cybercicco.dev.service;

import fr.cybercicco.dev.controller.message.ReservationResponse;
import fr.cybercicco.dev.dto.ReservationDTO;
import fr.cybercicco.dev.entity.Horaire;
import fr.cybercicco.dev.entity.Place;
import fr.cybercicco.dev.entity.Reservation;
import fr.cybercicco.dev.exception.InvalidReservationException;
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
        LocalDate ldDebut = (date.charAt(4)!='-')
                ?LocalDate.now()
                : LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
        LocalDateTime ldtDebut = currentDate.atTime(
                (soir)
                        ? horaireForWeekDay.getOuvertureDiner()
                        : horaireForWeekDay.getOuvertureDejeuner());
        LocalDateTime ldtFin = currentDate.atTime(
                (soir)
                        ? horaireForWeekDay.getFermetureDiner()
                        : horaireForWeekDay.getFermetureDejeuner());
        return new LocalDateTime[]{ldtDebut, ldtFin};
    }

    @Transactional
    public ReservationResponse saveReservations(ReservationDTO reservationDTO, String authorization) {
        reservationDTO.setNbPlaces(reservationDTO.getNbPlaces()+1);
        String email = authorization!=null ? jwtService.extractEmail(authorization.substring(7)):null;
        if(!isDateValid(reservationDTO)) throw new InvalidReservationException("Réservation non valide");
        LocalDateTime[] rangeForCurrentDate = getHoraireRangeInCurrentDate(
                reservationDTO.getDateReservation().toLocalDate(),
                reservationDTO.isSoir(),
                reservationDTO.getRestaurant());
        List<Place> places = placeRepository.findAllFreePlaces(
                rangeForCurrentDate[0],
                rangeForCurrentDate[1],
                reservationDTO.getRestaurant());
        List<Place> chosenPlaces = findBestPlaceToReserve(places, reservationDTO.getNbPlaces());
        chosenPlaces.forEach((var)-> saveOneReservation(reservationDTO, var, email));
        return getReservationFromCurrentDate(
                reservationDTO.getDateReservation().toLocalDate(),
                reservationDTO.isSoir(),
                reservationDTO.getRestaurant());
    }

    private boolean isDateValid(ReservationDTO reservationDTO) {
        LocalDateTime[] range = getHoraireRangeInCurrentDate(
                reservationDTO.getDateReservation().toLocalDate(),
                reservationDTO.isSoir(),
                reservationDTO.getRestaurant());
        LocalDateTime upperLimit = range[1].minusHours(1);
        int placesRestantes =  placeRepository.findAllFreePlaces(range[0], range[1], reservationDTO.getRestaurant()).stream()
                .map(place -> place.getNbPlaces().intValueExact())
                .mapToInt(Integer::intValue)
                .sum();
        return (!reservationDTO.getDateReservation().isBefore(range[0])
                && !reservationDTO.getDateReservation().isAfter(upperLimit))
                && ((reservationDTO.getNbPlaces() < placesRestantes));
    }

    /**
     * @param places : Liste d'objet Place.
     * @param nbPlaces : nombre de place souhaité par la personne faisant la réservation
     * @return List de place : la liste des places permettant de répondre au mieux à la demande de places.
     * Parmis la liste des tables non réservées, va essayer de déterminer toutes les combinaisons de tables
     * pouvant contenir le nombre de personnes souhaitées.
     * Parmis ces combinaisons, il va retenir la dernière combinaison de tables permettant de contenir au mieux
     * ces personnes sans gacher de place, et va renvoyer la liste des tables qu'il faut donc réserver compte tenu
     * du nombre de personnes à réserver.
     * Cet algorithme fonctionne de la façon suivante :
     * Itère une première fois sur le tableau. S'il trouve une table suffisamment grande, il la met dans une map, avec
     * pour clé le nombre de places disponibles grâce à cette table.
     * Si la table n'est pas assez grande, il itère ensuite une nouvelle fois sur les autres tables, jusqu'à ce que la
     * combinaison de toutes les tables possède suffisamment de place, ou qu'aucune combinaison n'ait été trouvée
     * S'il y a suffisamment de place, met la liste des tables dans la map avec pour clé leur nombre de places.
     * Une fois ceci fait, trie la liste des clés de la map de plus petite à la plus grande, et renvoie la liste de
     * tables possédant le nombre de places le plus proche du nombre de places souhaité
     */
    public List<Place> findBestPlaceToReserve(List<Place> places, Integer nbPlaces){
        Map<Integer, List<Place>> sizeToPlaces = new HashMap<>();

        for(int i = 0; i < places.size(); i++){
            if(places.get(i).getNbPlaces().intValueExact() < nbPlaces){
                for(int j = 0; j < places.size(); j++){
                    if(i != j){
                        List<Place> placesForOneRow = new ArrayList<>();
                        placesForOneRow.add(places.get(i));
                        int k = 0;
                        while(placesForOneRow.stream()
                                .map(val-> val.getNbPlaces().intValueExact())
                                .mapToInt(Integer::intValue)
                                .sum() < nbPlaces
                                &&
                                j+k < places.size()){
                            placesForOneRow.add(places.get(j+k));
                            k++;
                        }
                        sizeToPlaces.put(placesForOneRow.stream()
                                .map(val-> val.getNbPlaces().intValueExact()).mapToInt(Integer::intValue)
                                .sum()*100 +placesForOneRow.size(),
                                placesForOneRow);
                    }
                }
            } else {
                sizeToPlaces.put(places.get(i).getNbPlaces().intValueExact()*100, List.of(places.get(i)));
            }
        }
        SortedSet<Integer> keys = new TreeSet<>(sizeToPlaces.keySet());
        for(Integer key : keys){
            if(key > nbPlaces*100){
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
                .nom(reservationDTO.getNom())
                .prenom(reservationDTO.getPrenom())
                .build();
        reservationRepository.save(reservation);
    }
}
