package co.com.ceiba.alquilerbicicletas.application.service;

import co.com.ceiba.alquilerbicicletas.domain.exception.DataNotFoundException;
import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.EstadoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.BicicletaComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioConsultaPuerto;

public class BicicletaComandoServicio implements BicicletaComandoPuerto {
    
    private static final String CAMBIAR_ESTADO = "No es posible cambiar el estado de una bicicleta que se encuentra alquilada.";
    private static final String NO_EXISTE = "No existe una bicicleta con código: ";
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

    @Override
    public Bicicleta cambiarEstado(String codigo, EstadoBicicleta nuevoEstado){
        Bicicleta bicicleta = repositorioConsulta.buscarPorCodigo(codigo)
                                                 .orElseThrow(() -> new DataNotFoundException(NO_EXISTE + codigo));
        
        if (bicicleta.getEstadoBicicleta() == EstadoBicicleta.ALQUILADA){
            throw new DataNotFoundException(CAMBIAR_ESTADO);
        }
        bicicleta.setEstadoBicicleta(nuevoEstado);
        return repositorioComando.guardar(bicicleta);
    }
}
