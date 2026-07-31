package co.com.ceiba.alquilerbicicletas.infrastructure.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.com.ceiba.alquilerbicicletas.domain.exception.DataNotFoundException;

@RestControllerAdvice
public class DataNotFoundHandler extends BaseExceptionHandler{

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Map<String, String>>
            manejar(DataNotFoundException ex) {
            return construirRespuestaError(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    
}
