package co.com.ceiba.alquilerbicicletas.infrastructure.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidacionHandler extends BaseExceptionHandler{
    
    @ExceptionHandler(
        MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>>
            manejar(MethodArgumentNotValidException ex) {
        String mensaje = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Datos inválidos");
        return construirRespuestaError(mensaje,HttpStatus.BAD_REQUEST);
    }
}
