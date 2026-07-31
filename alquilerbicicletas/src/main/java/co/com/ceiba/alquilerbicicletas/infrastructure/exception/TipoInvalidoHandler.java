package co.com.ceiba.alquilerbicicletas.infrastructure.exception;

import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(1)
public class TipoInvalidoHandler extends BaseExceptionHandler{

    private static final String MENSAJE = "El estado o tipo de DATO enviado no es válido";

    @ExceptionHandler(
        IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>>
            manejar(IllegalArgumentException ex) {
        return construirRespuestaError(
            MENSAJE, HttpStatus.BAD_REQUEST);
    }
}
