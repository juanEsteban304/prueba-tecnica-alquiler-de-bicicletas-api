package co.com.ceiba.alquilerbicicletas.infrastructure.exception;

import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.com.ceiba.alquilerbicicletas.domain.exception.BicicletaNoDisponibleException;

@RestControllerAdvice
@Order(1)
public class BicicletaNoDisponibleHandler extends BaseExceptionHandler {

    @ExceptionHandler(BicicletaNoDisponibleException.class)
    public ResponseEntity<Map<String, String>> manejar(
            BicicletaNoDisponibleException ex) {

        return construirRespuestaError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }
}