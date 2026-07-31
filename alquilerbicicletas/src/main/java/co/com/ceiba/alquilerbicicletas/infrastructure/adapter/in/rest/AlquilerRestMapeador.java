package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.in.rest;

import org.springframework.stereotype.Component;

import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;
import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;

@Component
public class AlquilerRestMapeador {
    public Alquiler convertirAEntidad(AlquilerDTO dto) {
        Bicicleta bicicleta = new Bicicleta(
            dto.getCodigoBicicleta(),
            null,
            null);
        return new Alquiler(
            bicicleta,
            dto.getNombreCliente(),
            null,
            dto.getDuracionEstimada(),
            null);
}
    public AlquilerDTO convertirADTO(Alquiler alquiler) {
        return new AlquilerDTO(
            alquiler.getBicicleta().getCodigo(),
            alquiler.getNombreCliente(),
            alquiler.getDuracionEstimada(),
            alquiler.getHoraInicio(),
            alquiler.getHoraFin(),
            alquiler.getTiempoRealUso(),
            alquiler.getCostoBase(),
            alquiler.getMulta(),
            alquiler.getCostoTotal());
    }
}
