package co.com.ceiba.alquilerbicicletas.domain.ports.in;

import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.EstadoBicicleta;

public interface BicicletaComandoPuerto {

    Bicicleta registrarBicicleta(Bicicleta bicicleta);

    Bicicleta cambiarEstado(String codigo, EstadoBicicleta estado);
    
}
