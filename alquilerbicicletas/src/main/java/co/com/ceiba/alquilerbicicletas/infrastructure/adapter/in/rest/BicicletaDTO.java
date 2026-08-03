package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.in.rest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class BicicletaDTO {

    private static final String TIPO_INVALIDO = "Tipo inválido. Valores permitidos: URBANA, MONTAÑA, ELECTRICA";
    private static final String ESTADO_INVALIDO = "Estado inválido. Valores permitidos: DISPONIBLE, ALQUILADA, EN_MANTENIMIENTO -> EN MANTENIMIENTO";
    private static final String ESTADO_OBLIGATORIO = "El Estado de la Bicicleta es obligatorio";
    private static final String TIPO_OBLIGATORIO = "El Tipo de Bicicleta es obligatorio";

    private String codigo;

    @NotBlank(message = ESTADO_OBLIGATORIO)
    @Pattern(
        regexp = "(?i)^(DISPONIBLE|ALQUILADA|EN(?:_| )MANTENIMIENTO)$",
        message = ESTADO_INVALIDO) 
    private String estado;

    @NotBlank(message = TIPO_OBLIGATORIO)
    @Pattern(
        regexp = "^(?i:URBANA|MONTAÑA|montaña|ELECTRICA)$",
        message = TIPO_INVALIDO)
    private String tipo;

    public BicicletaDTO() {
    }

    public BicicletaDTO(String codigo, String estado, String tipo) {
        this.codigo = codigo;
        this.estado = estado;
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
