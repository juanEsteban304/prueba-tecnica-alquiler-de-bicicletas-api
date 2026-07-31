package co.com.ceiba.alquilerbicicletas.domain.exception;

public class BicicletaNoDisponibleException extends ApplicationException {
    public BicicletaNoDisponibleException(String mensaje){
        super(mensaje);
    }
}
