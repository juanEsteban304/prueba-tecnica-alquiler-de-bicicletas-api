package co.com.ceiba.alquilerbicicletas.application.service;

import java.util.List;

import co.com.ceiba.alquilerbicicletas.domain.exception.DataNotFoundException;
import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.AlquilerConsultaPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.AlquilerRepositorioConsultaPuerto;

public class AlquilerConsultaServicio implements AlquilerConsultaPuerto {

    private static final String ERROR = "No existe historial de alquileres para la bicicleta: ";
    private final AlquilerRepositorioConsultaPuerto repositorioConsulta;

    public AlquilerConsultaServicio (AlquilerRepositorioConsultaPuerto repositorioConsulta){
        this.repositorioConsulta = repositorioConsulta;
    }

    @Override
    public List<Alquiler> listar(){
        return repositorioConsulta.listar();
    }

    @Override
    public List<Alquiler> buscarHistorial(String codigoBicicleta) {
        List<Alquiler> historial = repositorioConsulta.buscarHistorial(codigoBicicleta);

        if (historial.isEmpty()){
            throw new DataNotFoundException(ERROR + codigoBicicleta);
        }
        return repositorioConsulta.buscarHistorial(codigoBicicleta);
    }  
}
