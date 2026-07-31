package co.com.ceiba.alquilerbicicletas.domain.model;

import java.time.LocalDateTime;

public class Alquiler {
    
    private Bicicleta bicicleta;
    private String nombreCliente;
    private LocalDateTime horaInicio;
    private Integer duracionEstimada;
    private LocalDateTime horaFin;

    public Alquiler(Bicicleta bicicleta, String nombreCliente, LocalDateTime horaInicio, Integer duracionEstimada,
            LocalDateTime horaFin) {
        this.bicicleta = bicicleta;
        this.nombreCliente = nombreCliente;
        this.horaInicio = horaInicio;
        this.duracionEstimada = duracionEstimada;
        this.horaFin = horaFin;
    }

    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Integer getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(Integer duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    public void asignarBicicleta(Bicicleta bicicleta) {
    this.bicicleta = bicicleta;
    }

    public LocalDateTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalDateTime horaFin) {
        this.horaFin = horaFin;
    }

    
}
