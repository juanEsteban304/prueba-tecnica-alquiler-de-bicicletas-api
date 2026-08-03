package co.com.ceiba.alquilerbicicletas.application.service;

import java.util.List;

import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.AlquilerConsultaPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.AlquilerRepositorioConsultaPuerto;

public class AlquilerConsultaServicio implements AlquilerConsultaPuerto {
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
        return repositorioConsulta.buscarHistorial(codigoBicicleta);
    }  
}
