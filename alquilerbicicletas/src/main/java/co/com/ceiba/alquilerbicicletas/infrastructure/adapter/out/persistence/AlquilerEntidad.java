package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence;

import java.math.BigDecimal;
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

    @Column(name = "nombre_cliente",nullable = false)
    private String nombreCliente;

    @Column(name = "hora_inicio", nullable = false)
    private LocalDateTime horaInicio;

    @Column(name = "duracion_estimada", nullable = false)
    private Integer duracionEstimada;

    @Column(name = "hora_fin")
    private LocalDateTime horaFin;

    @Column(name = "tiempo_real_uso")
    private Integer tiempoRealUso;

    @Column(name = "Costo_base")
    private BigDecimal costoBase;

    @Column(name = "multa")
    private BigDecimal multa;

    @Column(name = "costo_total")
    private BigDecimal costoTotal;

    public AlquilerEntidad() {
    }

    public AlquilerEntidad(BicicletaEntidad bicicleta, String nombreCliente, LocalDateTime horaInicio,
                           Integer duracionEstimada, LocalDateTime horaFin, Integer tiempoRealUso ,
                           BigDecimal costoBase,BigDecimal multa, BigDecimal costoTotal) {
        this.bicicleta = bicicleta;
        this.nombreCliente = nombreCliente;
        this.horaInicio = horaInicio;
        this.duracionEstimada = duracionEstimada;
        this.horaFin = horaFin;
        this.tiempoRealUso = tiempoRealUso;
        this.costoBase = costoBase;
        this.multa = multa;
        this.costoTotal = costoTotal;
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

    public Integer getTiempoRealUso() {
        return tiempoRealUso;
    }

    public void setTiempoRealUso(Integer tiempRealUso) {
        this.tiempoRealUso = tiempRealUso;
    }

    public BigDecimal getCostoBase() {
        return costoBase;
    }

    public void setCostoBase(BigDecimal costoBase) {
        this.costoBase = costoBase;
    }

    public BigDecimal getMulta() {
        return multa;
    }

    public void setMulta(BigDecimal multa) {
        this.multa = multa;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }
}
