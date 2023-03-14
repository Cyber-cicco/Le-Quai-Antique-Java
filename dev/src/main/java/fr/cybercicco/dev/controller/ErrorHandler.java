package fr.cybercicco.dev.controller;

import fr.cybercicco.dev.controller.message.ReservationResponse;
import fr.cybercicco.dev.exception.DuplicateEntryException;
import fr.cybercicco.dev.exception.EntityNotFoundException;
import fr.cybercicco.dev.exception.UndefinedHorairesForWeekDayException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> quandValidationException(ValidationException ex) {
        if(ex instanceof ConstraintViolationException cEx) {
            cEx.getConstraintViolations();
        }
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> quandEntityNotFoundException(EntityNotFoundException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<String> quandDuplicateEntryException(DuplicateEntryException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(UndefinedHorairesForWeekDayException.class)
    public ResponseEntity<ReservationResponse> quandPasDHoraires(){
        return ResponseEntity.ok(new ReservationResponse(0));
    }
}
