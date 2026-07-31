package co.com.ceiba.alquilerbicicletas.domain.ports.in;

import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;

public interface BicicletaComandoPuerto {

    Bicicleta registrarBicicleta(Bicicleta bicicleta);
    
}
