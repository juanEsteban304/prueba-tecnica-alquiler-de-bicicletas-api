package co.com.ceiba.alquilerbicicletas.domain.ports.out;

import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;

public interface BicicletaRepositorioComandoPuerto {

    Bicicleta guardar (Bicicleta bicicleta);
    
}
