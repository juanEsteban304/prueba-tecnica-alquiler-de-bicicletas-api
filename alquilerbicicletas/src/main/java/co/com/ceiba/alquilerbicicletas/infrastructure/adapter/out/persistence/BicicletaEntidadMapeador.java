package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence;

import org.springframework.stereotype.Component;

import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;

@Component
public class BicicletaEntidadMapeador {

    public BicicletaEntidad converAEntidad(Bicicleta bicicleta){
        return new BicicletaEntidad(
            bicicleta.getCodigo(),
            bicicleta.getEstadoBicicleta(),
            bicicleta.getTipoBicicleta()
        );
    }

    public Bicicleta convertirADTO(BicicletaEntidad entidad){
        return new Bicicleta(
            entidad.getCodigo(),
            entidad.getEstadoBicicleta(),
            entidad.getTipoBicicleta()
        );
    }
    
}
