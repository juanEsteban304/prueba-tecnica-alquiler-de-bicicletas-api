package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.in.rest;

import org.springframework.stereotype.Component;

import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.EstadoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.TipoBicicleta;

@Component
public class BicicletaRestMapeador {

    public Bicicleta convertirAEntidad(BicicletaDTO dto){
        String estadoNormalizado = dto.getEstado().toUpperCase().replace(" ", "_");
        EstadoBicicleta estado = EstadoBicicleta.valueOf(estadoNormalizado);
        TipoBicicleta tipo = TipoBicicleta.valueOf(dto.getTipo().toUpperCase());
        return new Bicicleta(null, estado, tipo);
    }

    public BicicletaDTO convertirADTO(Bicicleta bicicleta){
        return new BicicletaDTO(
            bicicleta.getCodigo(),
            bicicleta.getEstadoBicicleta().name(),
            bicicleta.getTipoBicicleta().name()
        );
    }
}
