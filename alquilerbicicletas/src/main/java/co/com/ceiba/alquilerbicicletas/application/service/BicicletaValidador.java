package co.com.ceiba.alquilerbicicletas.application.service;

import co.com.ceiba.alquilerbicicletas.domain.exception.CodigoException;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioConsultaPuerto;

public class BicicletaValidador {

    private static final String MENSAJE_CODIGO_YA_EN_USO = "El codigo de la Bicicleta ya esta en uso";
    private BicicletaRepositorioConsultaPuerto repositorioConsulta;

    public BicicletaValidador(BicicletaRepositorioConsultaPuerto repositorioConsulta){
        this.repositorioConsulta = repositorioConsulta;
    }

    public void validarCodigoUnico(String codigo){
        if (repositorioConsulta.existePorCodigo(codigo)){
            throw new CodigoException(MENSAJE_CODIGO_YA_EN_USO);
        }
    }
    
}
