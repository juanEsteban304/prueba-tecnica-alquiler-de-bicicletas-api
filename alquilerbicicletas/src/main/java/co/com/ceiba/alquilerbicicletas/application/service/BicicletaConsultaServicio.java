package co.com.ceiba.alquilerbicicletas.application.service;

import java.util.List;

import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.EstadoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.TipoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.BicicletaConsultaPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioConsultaPuerto;

public class BicicletaConsultaServicio implements BicicletaConsultaPuerto{

    private final BicicletaRepositorioConsultaPuerto repositorioConsulta;

    public BicicletaConsultaServicio(BicicletaRepositorioConsultaPuerto repositorioConsulta){
        this.repositorioConsulta = repositorioConsulta;
    }

    @Override
    public List<Bicicleta> listar() {
        return repositorioConsulta.listar();
    }

    @Override
    public List<Bicicleta> buscar(String codigo, EstadoBicicleta estado, TipoBicicleta tipo){
        return repositorioConsulta.buscar(codigo, estado, tipo);
    }
    
}
