package co.com.ceiba.alquilerbicicletas.application.service;

import java.time.LocalDateTime;
import java.util.Optional;

import co.com.ceiba.alquilerbicicletas.domain.exception.DataNotFoundException;
import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;
import co.com.ceiba.alquilerbicicletas.domain.model.EstadoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.AlquilerFinalizacionPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.AlquilerRepositorioComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.AlquilerRepositorioConsultaPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioComandoPuerto;

public class AlquilerFinalizacionServicio implements AlquilerFinalizacionPuerto {

    private static final String NALQUILER_NO_ACTIVO = "No existe un alquiler activo para la bicicleta: ";
    private final AlquilerRepositorioConsultaPuerto repositorioConsulta;
    private final AlquilerRepositorioComandoPuerto repositorioComando;
    private final BicicletaRepositorioComandoPuerto biciletacomando;
    private final CalculadorCostoAlquiler calculadorCosto;

    public AlquilerFinalizacionServicio(AlquilerRepositorioConsultaPuerto repositorioConsulta,
            AlquilerRepositorioComandoPuerto repositorioComando, BicicletaRepositorioComandoPuerto biciletacomando,
            CalculadorCostoAlquiler costo) {
        this.repositorioConsulta = repositorioConsulta;
        this.repositorioComando = repositorioComando;
        this.biciletacomando = biciletacomando;
        this.calculadorCosto = costo;
    }
    
    @Override
    public Alquiler finalizarAlquiler(String codigoBicicleta){
        Optional<Alquiler> alquilerEncontrado = repositorioConsulta.buscarAlquilerActivo(codigoBicicleta);
        Alquiler alquiler = alquilerEncontrado.orElseThrow(() -> new DataNotFoundException(NALQUILER_NO_ACTIVO + codigoBicicleta));
        alquiler.setHoraFin(LocalDateTime.now());
        calculadorCosto.calcular(alquiler);
        alquiler.getBicicleta().setEstadoBicicleta(EstadoBicicleta.DISPONIBLE);
        biciletacomando.guardar(alquiler.getBicicleta());
        Alquiler alquilerFinalizado = repositorioComando.guardar(alquiler);
        return alquilerFinalizado;
    }
}
