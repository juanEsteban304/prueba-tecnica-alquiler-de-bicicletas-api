package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence;

import org.springframework.stereotype.Component;

import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.AlquilerRepositorioComandoPuerto;

@Component
public class AlquilerRepositorioAdaptador implements AlquilerRepositorioComandoPuerto{
    
    private final AlquilerJpaRepositorio jpaRepositorio;
    private final AlquilerEntidadMapeador mapeador;
    private final BicicletaJpaRepositorio bicicletaJpaRepositorio;

    public AlquilerRepositorioAdaptador(AlquilerJpaRepositorio jpaRepositorio, AlquilerEntidadMapeador mapeador, BicicletaJpaRepositorio bicicletaJpaRepositorio) {
        this.jpaRepositorio = jpaRepositorio;
        this.mapeador = mapeador;
        this.bicicletaJpaRepositorio = bicicletaJpaRepositorio;
    }

    @Override
    public Alquiler guardar(Alquiler alquiler){
        BicicletaEntidad bicicletaEntidad = bicicletaJpaRepositorio.getReferenceById(alquiler.getBicicleta().getCodigo());
        AlquilerEntidad  entidad = mapeador.convertirAEntidad(alquiler, bicicletaEntidad);
        AlquilerEntidad guardar = jpaRepositorio.save(entidad);
        return mapeador.convertirADTO(guardar);
    }
}
