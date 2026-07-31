package co.com.ceiba.alquilerbicicletas.domain.exception;

public class DataNotFoundException extends ApplicationException {
    public DataNotFoundException(String mensaje){
        super(mensaje);
    }
    
}
