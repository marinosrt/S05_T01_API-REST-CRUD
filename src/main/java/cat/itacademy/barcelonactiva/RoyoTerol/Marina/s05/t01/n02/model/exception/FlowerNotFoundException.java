package cat.itacademy.barcelonactiva.RoyoTerol.Marina.s05.t01.n02.model.exception;

public class FlowerNotFoundException extends RuntimeException{

    private String resourceName;
    private Integer idNumber;

    public FlowerNotFoundException(String resourceName, Integer idNumber) {
        super(String.format("'%s' not found with ID : '%s'", resourceName, idNumber));
        this.resourceName = resourceName;
        this.idNumber = idNumber;
    }

}
