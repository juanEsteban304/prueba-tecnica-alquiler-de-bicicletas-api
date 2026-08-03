package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.in.rest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class EstadoBicicletaDTO {

    private static final String ESTADO_OBLIGATORIO = "El estado es obligatorio.";

    private static final String ESTADO_INVALIDO = "Estado inválido. Valores permitidos: DISPONIBLE o EN_MANTENIMIENTO.";

    @NotBlank(message = ESTADO_OBLIGATORIO)
    @Pattern(
        regexp = "(?i)^(DISPONIBLE|EN(?:_| )MANTENIMIENTO)$",
        message = ESTADO_INVALIDO)
    private String estado;

    public EstadoBicicletaDTO() {
    }

    public EstadoBicicletaDTO(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}