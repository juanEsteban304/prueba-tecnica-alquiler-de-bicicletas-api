package co.com.ceiba.alquilerbicicletas.application.service;

import java.time.LocalDateTime;
import java.util.Optional;

import co.com.ceiba.alquilerbicicletas.domain.exception.BicicletaNoDisponibleException;
import co.com.ceiba.alquilerbicicletas.domain.exception.DataNotFoundException;
import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;
import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.EstadoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.AlquilerComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.AlquilerRepositorioComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioConsultaPuerto;

public class AlquilerComandoServicio implements AlquilerComandoPuerto{
    
    private static final String CODIGO_INEXISTENTE = "No existe una bicicleta con el codigo: ";
    private static final String BICICLETA_NO_DISPONIBLE = "La bicicleta %s no está disponible para alquiler";
    private final BicicletaRepositorioConsultaPuerto repositorioConsulta;
    private final BicicletaRepositorioComandoPuerto repositorioComando;
    private final AlquilerRepositorioComandoPuerto alquilerComando;

    public AlquilerComandoServicio(BicicletaRepositorioConsultaPuerto repositorioConsulta,
            BicicletaRepositorioComandoPuerto repositorioComando, AlquilerRepositorioComandoPuerto alquilerComando) {
        this.repositorioConsulta = repositorioConsulta;
        this.repositorioComando = repositorioComando;
        this.alquilerComando = alquilerComando;
    }
    
    @Override
    public Alquiler iniciarAlquiler(Alquiler alquiler){
        Optional<Bicicleta> bicicletaEncontrada = repositorioConsulta.buscarPorCodigo(alquiler.getBicicleta().getCodigo());
        Bicicleta bicicleta = bicicletaEncontrada
                              .orElseThrow(() -> new DataNotFoundException(CODIGO_INEXISTENTE
                              + alquiler.getBicicleta().getCodigo()));
        alquiler.asignarBicicleta(bicicleta);
        
        if (bicicleta.getEstadoBicicleta() != EstadoBicicleta.DISPONIBLE){
            throw new BicicletaNoDisponibleException(String.format(BICICLETA_NO_DISPONIBLE, bicicleta.getCodigo()));
        }
        bicicleta.setEstadoBicicleta(EstadoBicicleta.ALQUILADA);
        repositorioComando.guardar(bicicleta);
        alquiler.setHoraInicio(LocalDateTime.now());
        Alquiler alquilerGuardado = alquilerComando.guardar(alquiler);
        return alquilerGuardado;
    }
    
}
