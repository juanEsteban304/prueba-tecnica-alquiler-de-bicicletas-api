package co.com.ceiba.alquilerbicicletas.databuilder;

import java.time.LocalDateTime;

import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;
import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;


public class AlquilerTestDataBuilder {


    private Bicicleta bicicleta;
    private String nombreCliente = "Juan Garcia";
    private LocalDateTime horaInicio = LocalDateTime.now();
    private Integer duracionEstimada = 1;
    private LocalDateTime horaFin = null;

    public AlquilerTestDataBuilder conBicicleta(Bicicleta bicicleta){
        this.bicicleta = bicicleta;
        return this;
    }

    public AlquilerTestDataBuilder conNombreCliente(String nombreCliente){
        this.nombreCliente = nombreCliente;
        return this;
    }

    public AlquilerTestDataBuilder conHoraInicio(LocalDateTime horaInicio){
        this.horaInicio = horaInicio;
        return this;
    }

    public AlquilerTestDataBuilder conDuracionEstimada(Integer duracionEstimada){
        this.duracionEstimada = duracionEstimada;
        return this;
    }

    public AlquilerTestDataBuilder conHoraFin(LocalDateTime horaFin){
        this.horaFin = horaFin;
        return this;
    }

    public Alquiler build(){

        return new Alquiler(bicicleta,nombreCliente,horaInicio,duracionEstimada,horaFin);
    }

    public static AlquilerTestDataBuilder unAlquiler(){
        return new AlquilerTestDataBuilder();
    }

}