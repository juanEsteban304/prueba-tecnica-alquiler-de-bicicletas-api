package co.com.ceiba.alquilerbicicletas.aplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.com.ceiba.alquilerbicicletas.application.service.BicicletaCodigoGenerador;
import co.com.ceiba.alquilerbicicletas.application.service.BicicletaComandoServicio;
import co.com.ceiba.alquilerbicicletas.application.service.BicicletaValidador;
import co.com.ceiba.alquilerbicicletas.databuilder.BicicletaTestDataBuilder;
import co.com.ceiba.alquilerbicicletas.domain.exception.DataNotFoundException;
import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.EstadoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioConsultaPuerto;

@ExtendWith(MockitoExtension.class)
class BicicletaComandoServicioTest {
    @Mock
    private BicicletaRepositorioComandoPuerto repositorioComando;

    @Mock
    private BicicletaRepositorioConsultaPuerto repositorioConsulta;

    @Mock
    private BicicletaValidador bicicletaValidador;

    @Mock
    private BicicletaCodigoGenerador generador;

    @InjectMocks
    private BicicletaComandoServicio servicio;

    private static final String BIC_001 = "BIC-001";
    private static final String BIC_002 = "BIC-002";
    private static final String BIC_999 = "BIC-999";
    private static final String INEXISTE = "No existe una bicicleta con código: BIC-999";
    private static final String NO_POSIBLE_CAMBIAR_ESTADO = "No es posible cambiar el estado de una bicicleta que se encuentra alquilada.";

    @Test
    @DisplayName("Debe registrar bicicleta correctamente")
    void deberiaRegistrarBicicletaConCodigoInicialCuandoNoExistenRegistros() {
        Bicicleta bicicleta = BicicletaTestDataBuilder.unaBicicleta().build();
        Bicicleta bicicletaGuardada = BicicletaTestDataBuilder.unaBicicleta().conCodigo(BIC_001).build();

        when(repositorioConsulta.buscarUltimaBicicleta()).thenReturn(Optional.empty());
        when(generador.generarSiguienteCodigo(null)).thenReturn(BIC_001);
        when(repositorioComando.guardar(any(Bicicleta.class))).thenReturn(bicicletaGuardada);
 
        Bicicleta resultado = servicio.registrarBicicleta(bicicleta);

        assertNotNull(resultado);
        assertEquals(BIC_001, resultado.getCodigo());
        verify(repositorioConsulta).buscarUltimaBicicleta();
        verify(generador).generarSiguienteCodigo(null);
        verify(bicicletaValidador).validarCodigoUnico(BIC_001);
        verify(repositorioComando).guardar(any(Bicicleta.class));
    }

    @Test
    @DisplayName("Debe generar el siguiente código cuando ya existe una bicicleta")
    void deberiaRegistrarBicicletaConCodigoSiguiente() {
        Bicicleta ultimaBicicleta = BicicletaTestDataBuilder.unaBicicleta().conCodigo(BIC_001).build();
        Bicicleta bicicletaGuardada = BicicletaTestDataBuilder.unaBicicleta().conCodigo(BIC_002).build();

        when(repositorioConsulta.buscarUltimaBicicleta()).thenReturn(Optional.of(ultimaBicicleta));
        when(generador.generarSiguienteCodigo(BIC_001)).thenReturn(BIC_002);
        when(repositorioComando.guardar(any(Bicicleta.class))).thenReturn(bicicletaGuardada);

        Bicicleta bicicleta = BicicletaTestDataBuilder.unaBicicleta().build();
        Bicicleta resultado = servicio.registrarBicicleta(bicicleta);

        assertNotNull(resultado);
        assertEquals(BIC_002, resultado.getCodigo());
        verify(repositorioConsulta).buscarUltimaBicicleta();
        verify(generador).generarSiguienteCodigo(BIC_001);
        verify(repositorioComando).guardar(any(Bicicleta.class));
    }

    @Test
    @DisplayName("Debe cambiar el estado de una bicicleta correctamente")
    void deberiaCambiarEstadoBicicleta() {

        Bicicleta bicicletaDisponible = BicicletaTestDataBuilder.unaBicicleta().conEstado(EstadoBicicleta.DISPONIBLE).build();
        Bicicleta bicicletaMantenimiento = BicicletaTestDataBuilder.unaBicicleta().conEstado(EstadoBicicleta.EN_MANTENIMIENTO).build();

        when(repositorioConsulta.buscarPorCodigo(BIC_001)).thenReturn(Optional.of(bicicletaDisponible));
        when(repositorioComando.guardar(any(Bicicleta.class))).thenReturn(bicicletaMantenimiento);

        Bicicleta resultado = servicio.cambiarEstado(BIC_001,EstadoBicicleta.EN_MANTENIMIENTO);

        assertNotNull(resultado);
        assertEquals(EstadoBicicleta.EN_MANTENIMIENTO,resultado.getEstadoBicicleta());
        verify(repositorioConsulta).buscarPorCodigo(BIC_001);
        verify(repositorioComando).guardar(any(Bicicleta.class));
    }

    @Test
    @DisplayName("No debe permitir cambiar el estado de una bicicleta alquilada")
    void deberiaLanzarExcepcionCuandoLaBicicletaEstaAlquilada() {
        Bicicleta bicicleta = BicicletaTestDataBuilder.unaBicicleta().conEstado(EstadoBicicleta.ALQUILADA).build();

        when(repositorioConsulta.buscarPorCodigo(BIC_001)).thenReturn(Optional.of(bicicleta));

        DataNotFoundException excepcion = assertThrows(DataNotFoundException.class,() -> servicio.cambiarEstado(BIC_001,EstadoBicicleta.DISPONIBLE));

        assertEquals(NO_POSIBLE_CAMBIAR_ESTADO,excepcion.getMessage());
        verify(repositorioConsulta).buscarPorCodigo("BIC-001");
        verify(repositorioComando, never()).guardar(any(Bicicleta.class));
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando la bicicleta no existe")
    void deberiaLanzarExcepcionCuandoNoExisteLaBicicleta() {
        when(repositorioConsulta.buscarPorCodigo(BIC_999)).thenReturn(Optional.empty());

        DataNotFoundException excepcion = assertThrows(DataNotFoundException.class,() -> servicio.cambiarEstado(BIC_999,EstadoBicicleta.DISPONIBLE));

        assertEquals(INEXISTE,excepcion.getMessage());
        verify(repositorioConsulta).buscarPorCodigo(BIC_999);
        verify(repositorioComando, never()).guardar(any(Bicicleta.class));
    }
}

