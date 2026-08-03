package co.com.ceiba.alquilerbicicletas.aplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.com.ceiba.alquilerbicicletas.application.service.AlquilerComandoServicio;
import co.com.ceiba.alquilerbicicletas.databuilder.AlquilerTestDataBuilder;
import co.com.ceiba.alquilerbicicletas.databuilder.BicicletaTestDataBuilder;
import co.com.ceiba.alquilerbicicletas.domain.exception.BicicletaNoDisponibleException;
import co.com.ceiba.alquilerbicicletas.domain.exception.DataNotFoundException;
import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;
import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.EstadoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.AlquilerRepositorioComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioConsultaPuerto;


@ExtendWith(MockitoExtension.class)
class AlquilerComandoServicioTest {
    @Mock
    private BicicletaRepositorioConsultaPuerto repositorioConsulta;
    @Mock
    private BicicletaRepositorioComandoPuerto repositorioComando;
    @Mock
    private AlquilerRepositorioComandoPuerto alquilerComando;
    @InjectMocks
    private AlquilerComandoServicio servicio;

    private static final String BIC_001 = "BIC-001";

    @Test
    @DisplayName("Debe iniciar alquiler cuando bicicleta está disponible")
    void deberiaIniciarAlquilerCuandoBicicletaDisponible(){
        Bicicleta bicicleta =BicicletaTestDataBuilder.unaBicicleta().conCodigo(BIC_001).conEstado(EstadoBicicleta.DISPONIBLE).build();
        Alquiler alquiler = AlquilerTestDataBuilder.unAlquiler().build();
        alquiler.asignarBicicleta(bicicleta);

        when(repositorioConsulta.buscarPorCodigo(BIC_001))
                .thenReturn(Optional.of(bicicleta));
        when(alquilerComando.guardar(any(Alquiler.class)))
                .thenReturn(alquiler);

        Alquiler resultado =
                servicio.iniciarAlquiler(alquiler);

        assertNotNull(resultado);
        assertEquals(EstadoBicicleta.ALQUILADA,bicicleta.getEstadoBicicleta());
        verify(repositorioComando).guardar(bicicleta);
        verify(alquilerComando).guardar(alquiler);
    }



    @Test
    @DisplayName("Debe lanzar excepción cuando bicicleta no existe")
    void deberiaLanzarExcepcionCuandoBicicletaNoExiste(){
        Bicicleta bicicleta = BicicletaTestDataBuilder.unaBicicleta().conCodigo(BIC_001).build();
        Alquiler alquiler = AlquilerTestDataBuilder.unAlquiler().build();
        alquiler.asignarBicicleta(bicicleta);

        when(repositorioConsulta.buscarPorCodigo(BIC_001)).thenReturn(Optional.empty());

        DataNotFoundException excepcion = assertThrows(DataNotFoundException.class,() -> servicio.iniciarAlquiler(alquiler));

        assertEquals("No existe una bicicleta con el codigo: " + BIC_001,excepcion.getMessage());
    }

    @Test
    @DisplayName("Debe impedir alquilar bicicleta que no está disponible")
    void deberiaLanzarExcepcionCuandoBicicletaNoDisponible(){
        Bicicleta bicicleta =BicicletaTestDataBuilder.unaBicicleta().conCodigo(BIC_001).conEstado(EstadoBicicleta.ALQUILADA).build();
       Alquiler alquiler = AlquilerTestDataBuilder.unAlquiler().build();
        alquiler.asignarBicicleta(bicicleta);

        when(repositorioConsulta.buscarPorCodigo(BIC_001)).thenReturn(Optional.of(bicicleta));

        BicicletaNoDisponibleException excepcion = assertThrows(BicicletaNoDisponibleException.class,() -> servicio.iniciarAlquiler(alquiler));

        assertEquals("La bicicleta BIC-001 no está disponible para alquiler",excepcion.getMessage());
    }

}