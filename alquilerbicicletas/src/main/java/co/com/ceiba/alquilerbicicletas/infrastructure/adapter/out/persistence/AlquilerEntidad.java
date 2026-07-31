package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alquiler_Bicicletas")
public class AlquilerEntidad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bicicleta_codigo",
                referencedColumnName = "codigo",
                nullable = false)
    private BicicletaEntidad bicicleta;

    @Column(nullable = false)
    private String nombreCliente;

    @Column(nullable = false)
    private LocalDateTime horaInicio;

    @Column(nullable = false)
    private Integer duracionEstimada;

    @Column
    private LocalDateTime horaFin;

    public AlquilerEntidad() {
    }

    public AlquilerEntidad(BicicletaEntidad bicicleta, String nombreCliente, LocalDateTime horaInicio,
                           Integer duracionEstimada, LocalDateTime horaFin) {
        this.bicicleta = bicicleta;
        this.nombreCliente = nombreCliente;
        this.horaInicio = horaInicio;
        this.duracionEstimada = duracionEstimada;
        this.horaFin = horaFin;
    }

    public Long getId() {
        return id;
    }

    public BicicletaEntidad getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(BicicletaEntidad bicicleta) {
        this.bicicleta = bicicleta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
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

    public LocalDateTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalDateTime horaFin) {
        this.horaFin = horaFin;
    }
}
