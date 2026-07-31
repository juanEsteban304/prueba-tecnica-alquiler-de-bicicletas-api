package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.in.rest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AlquilerDTO {
    
    @NotBlank(message = "El código de la bicicleta es obligatorio")
    @Pattern(
        regexp = "^BIC-\\d{3,}$",
        message = "El código debe tener el formato BIC-001")
    private String codigoBicicleta;

    @NotBlank(message = "El nombre es obligatorio")
    @Pattern(
        regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$",
        message = "El nombre solo puede contener letras (a-z, A-z)")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombreCliente;

    @NotNull(message = "La duración estimada es obligatoria")
    @Min(value = 1, message = "La duración estimada debe ser de al menos 1 hora")
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
