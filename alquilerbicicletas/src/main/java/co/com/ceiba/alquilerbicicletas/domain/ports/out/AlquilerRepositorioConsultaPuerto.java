package co.com.ceiba.alquilerbicicletas.domain.ports.out;

import java.util.List;
import java.util.Optional;

import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;

public interface AlquilerRepositorioConsultaPuerto {
    Optional<Alquiler> buscarAlquilerActivo(String codigoBicicleta);

    List<Alquiler> listar();

    List<Alquiler> buscarHistorial(String codigoBicicleta);
}
