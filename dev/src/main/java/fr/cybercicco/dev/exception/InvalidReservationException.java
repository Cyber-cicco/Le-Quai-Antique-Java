package fr.cybercicco.dev.exception;

public class InvalidReservationException extends RuntimeException{

    public InvalidReservationException(String message){
        super(message);
    }

}
