package co.com.ceiba.alquilerbicicletas.domain.ports.in;

import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;

public interface AlquilerFinalizacionPuerto {
    Alquiler finalizarAlquiler(String codigoBicicleta);
}
