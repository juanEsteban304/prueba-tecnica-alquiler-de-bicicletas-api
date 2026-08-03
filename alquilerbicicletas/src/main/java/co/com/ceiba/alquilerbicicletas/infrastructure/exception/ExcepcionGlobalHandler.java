package co.com.ceiba.alquilerbicicletas.infrastructure.exception;

import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class ExcepcionGlobalHandler extends BaseExceptionHandler {

    private static final String ERROR_INTERNO =
            "Ha ocurrido un error interno del sistema";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> manejarExcepcionGlobal(
            Exception ex) {

                System.err.println("========== ERROR ==========");
                ex.printStackTrace();
                System.err.println("===========================");

        return construirRespuestaError(
                ERROR_INTERNO,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}