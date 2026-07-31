package co.com.ceiba.alquilerbicicletas.domain.ports.out;

import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;

public interface AlquilerRepositorioComandoPuerto {
    Alquiler guardar(Alquiler alquiler);
}
