package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence;

import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;

public class AlquilerMapeador {
    
    public void actualizarEntidad(AlquilerEntidad entidad,Alquiler alquiler) {
    
        entidad.setHoraFin(alquiler.getHoraFin());
        entidad.setTiempoRealUso(alquiler.getTiempoRealUso());
        entidad.setCostoBase(alquiler.getCostoBase());
        entidad.setMulta(alquiler.getMulta());
        entidad.setCostoTotal(alquiler.getCostoTotal());
    }
}
