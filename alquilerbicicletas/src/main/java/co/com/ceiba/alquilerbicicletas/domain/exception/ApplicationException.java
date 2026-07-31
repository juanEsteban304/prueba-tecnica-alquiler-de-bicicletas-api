package co.com.ceiba.alquilerbicicletas.domain.exception;

public class ApplicationException extends RuntimeException {
    public ApplicationException(String mensaje){
        super(mensaje);
    }
}
