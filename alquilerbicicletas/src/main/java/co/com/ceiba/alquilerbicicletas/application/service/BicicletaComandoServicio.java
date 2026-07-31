package co.com.ceiba.alquilerbicicletas.application.service;

import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.BicicletaComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioConsultaPuerto;

public class BicicletaComandoServicio implements BicicletaComandoPuerto {
    
    private final BicicletaRepositorioComandoPuerto repositorioComando;
    private final BicicletaValidador bicicletaValidador;
    private final BicicletaRepositorioConsultaPuerto repositorioConsulta;
    private final BicicletaCodigoGenerador generador;

    public BicicletaComandoServicio (BicicletaRepositorioComandoPuerto repositorioComando,
                                     BicicletaValidador bicicletaValidador,
                                     BicicletaRepositorioConsultaPuerto repositorioConsulta,
                                     BicicletaCodigoGenerador generador) {
        this.repositorioComando = repositorioComando;
        this.bicicletaValidador = bicicletaValidador;
        this.repositorioConsulta = repositorioConsulta;
        this.generador = generador;
    }

    @Override
    public Bicicleta registrarBicicleta(Bicicleta bicicleta){
        String ultimoCodigo = repositorioConsulta
                              .buscarUltimaBicicleta()
                              .map(Bicicleta::getCodigo)
                              .orElse(null);

        String nuevoCodigo = generador.generarSiguienteCodigo(ultimoCodigo);

        Bicicleta bicicletaNueva = Bicicleta.crear(nuevoCodigo,
                                                         bicicleta.getEstadoBicicleta(),
                                                         bicicleta.getTipoBicicleta());

        bicicletaValidador.validarCodigoUnico(bicicletaNueva.getCodigo());
        Bicicleta registro = repositorioComando.guardar(bicicletaNueva);
        return registro;
    }
}
