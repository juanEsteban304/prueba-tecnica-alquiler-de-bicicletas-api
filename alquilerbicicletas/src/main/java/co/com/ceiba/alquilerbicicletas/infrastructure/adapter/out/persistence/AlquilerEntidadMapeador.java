package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence;

import org.springframework.stereotype.Component;

import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;

@Component
public class AlquilerEntidadMapeador {

    private final BicicletaEntidadMapeador entidadMapeador;

    public AlquilerEntidadMapeador(BicicletaEntidadMapeador entidadMapeador) {
        this.entidadMapeador = entidadMapeador;
    }

    public AlquilerEntidad convertirAEntidad(Alquiler alquiler, BicicletaEntidad bicicletaEntidad ){
        return new AlquilerEntidad(
            bicicletaEntidad,
            alquiler.getNombreCliente(),
            alquiler.getHoraInicio(),
            alquiler.getDuracionEstimada(),
            alquiler.getHoraFin()
        );
    }

    public Alquiler convertirADTO(AlquilerEntidad alquilerEntidad){
        return new Alquiler(entidadMapeador.convertirADTO(alquilerEntidad.getBicicleta()),
        alquilerEntidad.getNombreCliente(),
        alquilerEntidad.getHoraInicio(),
        alquilerEntidad.getDuracionEstimada(),
        alquilerEntidad.getHoraFin());
    }
}
