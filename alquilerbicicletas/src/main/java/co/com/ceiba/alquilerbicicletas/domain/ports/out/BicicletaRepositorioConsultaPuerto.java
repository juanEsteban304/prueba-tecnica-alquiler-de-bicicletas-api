package co.com.ceiba.alquilerbicicletas.domain.ports.out;

import java.util.List;
import java.util.Optional;

import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.EstadoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.TipoBicicleta;

public interface BicicletaRepositorioConsultaPuerto {

    Optional<Bicicleta> buscarPorCodigo (String codigo);
    
    boolean existePorCodigo(String codigo);

    Optional<Bicicleta> buscarUltimaBicicleta();

    List<Bicicleta> listar();

    List<Bicicleta> buscar(String codigo,EstadoBicicleta estado,TipoBicicleta tipo);
}
