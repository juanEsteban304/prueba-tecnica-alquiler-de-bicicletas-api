package co.com.ceiba.alquilerbicicletas.infrastructure.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseExceptionHandler {

    private static final String CLAVE_ERROR = "error";
    
    protected ResponseEntity<Map<String, String>>
            construirRespuestaError(
                String mensaje, HttpStatus status) {
        Map<String, String> error = new HashMap<>();
        error.put(CLAVE_ERROR, mensaje);
        return ResponseEntity.status(status).body(error);
    }
}
