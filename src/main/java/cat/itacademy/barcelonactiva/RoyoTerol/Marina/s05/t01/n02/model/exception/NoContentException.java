package cat.itacademy.barcelonactiva.RoyoTerol.Marina.s05.t01.n02.model.exception;

public class NoContentException extends RuntimeException{

    private String message;

    public NoContentException(String message) {
        super(message);
    }

}
