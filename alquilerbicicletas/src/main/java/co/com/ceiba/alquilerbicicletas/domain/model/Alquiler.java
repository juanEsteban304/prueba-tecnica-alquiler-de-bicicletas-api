package co.com.ceiba.alquilerbicicletas.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Alquiler {
    
    private Bicicleta bicicleta;
    private String nombreCliente;
    private LocalDateTime horaInicio;
    private Integer duracionEstimada;
    private LocalDateTime horaFin;

    private Integer tiempoRealUso;
    private BigDecimal costoBase;
    private BigDecimal multa;
    private BigDecimal costoTotal;

    public Alquiler(Bicicleta bicicleta, String nombreCliente, LocalDateTime horaInicio, Integer duracionEstimada,
            LocalDateTime horaFin, Integer tiempoRealUso,BigDecimal costoBase, BigDecimal multa, BigDecimal costoTotal) {
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

     public Integer getTiempoRealUso() {
        return tiempoRealUso;
    }

    public void setTiempoRealUso(Integer tiempoRealUso) {
        this.tiempoRealUso = tiempoRealUso;
    }
    
    public BigDecimal getCostoBase(){
        return costoBase;
    }

    public void setCostoBase(BigDecimal costoBase){
        this.costoBase = costoBase;
    }

    public BigDecimal getMulta(){
        return multa;
    }

    public void setMulta(BigDecimal multa){
        this.multa = multa;
    }

    public BigDecimal getCostoTotal(){
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal){
        this.costoTotal = costoTotal;
    }

    
}
