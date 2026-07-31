package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence;

import java.util.Optional;

import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.AlquilerRepositorioConsultaPuerto;

public class AlquilerRepositorioConsultaAdaptador implements AlquilerRepositorioConsultaPuerto{
    
    private final AlquilerJpaRepositorio jpa;
    private final AlquilerEntidadMapeador mapeador;

    public AlquilerRepositorioConsultaAdaptador(AlquilerJpaRepositorio jpa, AlquilerEntidadMapeador mapeador) {
        this.jpa = jpa;
        this.mapeador = mapeador;
    }

    @Override
    public Optional<Alquiler> buscarAlquilerActivo(String codigoBicicleta){
        return jpa.findByBicicleta_CodigoAndHoraFinIsNull(codigoBicicleta).map(mapeador::convertirADTO);
    }

}
