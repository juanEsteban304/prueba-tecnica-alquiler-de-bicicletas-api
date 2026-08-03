package co.com.ceiba.alquilerbicicletas.domain.ports.in;

import java.util.List;

import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;

public interface AlquilerConsultaPuerto {

    List<Alquiler> listar();

    List<Alquiler> buscarHistorial(String codigoBicicleta);
}
