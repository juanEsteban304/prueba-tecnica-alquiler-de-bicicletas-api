package co.com.ceiba.alquilerbicicletas.domain.ports.in;

import java.util.List;

import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.EstadoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.TipoBicicleta;

public interface BicicletaConsultaPuerto {
    List<Bicicleta> listar();
    
    List<Bicicleta> buscar(String codigo, EstadoBicicleta estado, TipoBicicleta tipo);
}
