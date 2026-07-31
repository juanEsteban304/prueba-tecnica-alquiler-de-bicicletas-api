package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence;

import java.util.Optional;

import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.AlquilerRepositorioComandoPuerto;

public class AlquilerRepositorioAdaptador implements AlquilerRepositorioComandoPuerto{
    
    private final AlquilerJpaRepositorio jpaRepositorio;
    private final AlquilerEntidadMapeador mapeador;
    private final BicicletaJpaRepositorio bicicletaJpaRepositorio;
    private final AlquilerMapeador mapeadorActualizar;

    public AlquilerRepositorioAdaptador(AlquilerJpaRepositorio jpaRepositorio, AlquilerEntidadMapeador mapeador, 
                                        BicicletaJpaRepositorio bicicletaJpaRepositorio, AlquilerMapeador mapeadorActualizar) {
        this.jpaRepositorio = jpaRepositorio;
        this.mapeador = mapeador;
        this.bicicletaJpaRepositorio = bicicletaJpaRepositorio;
        this.mapeadorActualizar = mapeadorActualizar;
    }

    @Override
    public Alquiler guardar(Alquiler alquiler){
        Optional<AlquilerEntidad>alquilerExistente = jpaRepositorio.findByBicicleta_CodigoAndHoraFinIsNull(alquiler.getBicicleta().getCodigo());
        if (alquilerExistente.isPresent()){
            AlquilerEntidad entidad = alquilerExistente.get();
            mapeadorActualizar.actualizarEntidad(entidad, alquiler);
            AlquilerEntidad actualizado = jpaRepositorio.save(entidad);
            return mapeador.convertirADTO(actualizado);
        }
        BicicletaEntidad bicicletaEntidad = bicicletaJpaRepositorio.getReferenceById(alquiler.getBicicleta().getCodigo());
        AlquilerEntidad  entidad = mapeador.convertirAEntidad(alquiler, bicicletaEntidad);
        AlquilerEntidad guardar = jpaRepositorio.save(entidad);
        return mapeador.convertirADTO(guardar);
    }
}
