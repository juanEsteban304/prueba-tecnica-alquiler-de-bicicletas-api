package co.com.ceiba.alquilerbicicletas.domain.ports.in;

import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;

public interface AlquilerComandoPuerto {
    
    Alquiler iniciarAlquiler(Alquiler alquiler);
}
