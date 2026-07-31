package co.com.ceiba.alquilerbicicletas.domain.ports.out;

import java.util.Optional;

import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;

public interface BicicletaRepositorioConsultaPuerto {

    Optional<Bicicleta> buscarPorCodigo (String codigo);
    
    boolean existePorCodigo(String codigo);

    Optional<Bicicleta> buscarUltimaBicicleta();
}
