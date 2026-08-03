package co.com.ceiba.alquilerbicicletas.application.service;

import java.util.List;

import co.com.ceiba.alquilerbicicletas.domain.exception.DataNotFoundException;
import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.EstadoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.TipoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.BicicletaConsultaPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioConsultaPuerto;

public class BicicletaConsultaServicio implements BicicletaConsultaPuerto{

    private static final String TIPO = " tipo= ";
    private static final String ESTADO = " estado= ";
    private static final String CODIGO = " código= ";
    private static final String NO_ENCONTRADO = "No se encontraron bicicletas con ";
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
        List<Bicicleta> bicicletas = repositorioConsulta.buscar(codigo, estado, tipo);

        if (bicicletas.isEmpty()){

            StringBuilder mensaje = new StringBuilder(NO_ENCONTRADO);
        
            if(codigo != null){
                mensaje.append(CODIGO).append(codigo);
            }

            if(estado != null){
                mensaje.append(ESTADO).append(estado);
            }

            if(tipo != null){
                mensaje.append(TIPO).append(tipo);
            }

            throw new DataNotFoundException(mensaje.toString());
        }
        return bicicletas;
    }
    
}
