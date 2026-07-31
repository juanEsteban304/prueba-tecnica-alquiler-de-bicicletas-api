package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioConsultaPuerto;

@Component
public class BicicletaRepositorioAdaptador implements BicicletaRepositorioConsultaPuerto, BicicletaRepositorioComandoPuerto{
    
    private final BicicletaJpaRepositorio jpaRepositorio;
    private final BicicletaEntidadMapeador mapeador;

    public BicicletaRepositorioAdaptador(BicicletaJpaRepositorio jpaRepositorio, BicicletaEntidadMapeador mapeador) {
        this.jpaRepositorio = jpaRepositorio;
        this.mapeador = mapeador;
    }

    @Override
    public Bicicleta guardar(Bicicleta bicicleta) {
        BicicletaEntidad entidad = mapeador.converAEntidad(bicicleta);
        BicicletaEntidad guardar = jpaRepositorio.save(entidad);
        return mapeador.convertirADTO(guardar);
    }

    @Override
    public boolean existePorCodigo(String codigo){
        return jpaRepositorio.existsByCodigo(codigo);
    }

    @Override
    public Optional<Bicicleta> buscarPorCodigo(String codigo){
        return jpaRepositorio.findById(codigo).map(mapeador::convertirADTO);
    }

    @Override
    public Optional<Bicicleta> buscarUltimaBicicleta(){
        return jpaRepositorio.findTopByOrderByCodigoDesc().map(mapeador::convertirADTO);
    }
}
