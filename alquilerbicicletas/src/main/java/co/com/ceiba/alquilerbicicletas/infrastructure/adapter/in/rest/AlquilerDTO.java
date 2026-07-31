package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.in.rest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AlquilerDTO {
    
    private static final String MENSAJE_DURACION = "La duración estimada debe ser de al menos 1 hora";
    private static final String DURACION = "La duración estimada es obligatoria";
    private static final String CARACTERES_MINIMOS = "El nombre debe tener entre 3 y 100 caracteres";
    private static final String VALIDACION_NOMBRE = "El nombre solo puede contener letras (a-z, A-z)";
    private static final String NOMBRE_OBLIGATORIO = "El nombre es obligatorio";
    private static final String FORMATO_CODIGO = "El código debe tener el formato BIC-001";
    private static final String CÓDIGO_OBLIGATORIO = "El código de la bicicleta es obligatorio";

    @NotBlank(message = CÓDIGO_OBLIGATORIO)
    @Pattern(
        regexp = "^BIC-\\d{3,}$",
        message = FORMATO_CODIGO)
    private String codigoBicicleta;

    @NotBlank(message = NOMBRE_OBLIGATORIO)
    @Pattern(
        regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$",
        message = VALIDACION_NOMBRE)
    @Size(min = 3, max = 100, message = CARACTERES_MINIMOS)
    private String nombreCliente;

    @NotNull(message = DURACION)
    @Min(value = 1, message = MENSAJE_DURACION)
    private Integer duracionEstimada;

    public AlquilerDTO(){
    }

    public AlquilerDTO(String codigoBicicleta,String nombreCliente,Integer suracionEstimada) {
        this.codigoBicicleta = codigoBicicleta;
        this.nombreCliente = nombreCliente;
        this.duracionEstimada = suracionEstimada;
    }

    public String getCodigoBicicleta() {
        return codigoBicicleta;
    }

    public void setCodigoBicicleta(String bicicleta) {
        this.codigoBicicleta = bicicleta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Integer getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(Integer suracionEstimada) {
        this.duracionEstimada = suracionEstimada;
    }
}
