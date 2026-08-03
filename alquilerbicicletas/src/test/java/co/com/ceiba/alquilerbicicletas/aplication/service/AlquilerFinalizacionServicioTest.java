package co.com.ceiba.alquilerbicicletas.aplication.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import co.com.ceiba.alquilerbicicletas.application.service.AlquilerFinalizacionServicio;
import co.com.ceiba.alquilerbicicletas.application.service.CalculadorCostoAlquiler;
import co.com.ceiba.alquilerbicicletas.databuilder.AlquilerTestDataBuilder;
import co.com.ceiba.alquilerbicicletas.databuilder.BicicletaTestDataBuilder;
import co.com.ceiba.alquilerbicicletas.domain.exception.DataNotFoundException;
import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;
import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.EstadoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.AlquilerRepositorioComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.AlquilerRepositorioConsultaPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioComandoPuerto;

@ExtendWith(MockitoExtension.class)
class AlquilerFinalizacionServicioTest {
    @Mock
    private AlquilerRepositorioConsultaPuerto repositorioConsulta;
    @Mock
    private AlquilerRepositorioComandoPuerto repositorioComando;
    @Mock
    private BicicletaRepositorioComandoPuerto bicicletaComando;
    @Mock
    private CalculadorCostoAlquiler calculadorCosto;
    @InjectMocks
    private AlquilerFinalizacionServicio servicio;

    private static final String BIC_001 = "BIC-001";
    private static final String NO_EXISTE_ALQUILER_ACTIVO = "No existe un alquiler activo para la bicicleta: ";

    @Test
    @DisplayName("Debe finalizar alquiler correctamente")
    void deberiaFinalizarAlquilerCorrectamente() {
        Bicicleta bicicleta = BicicletaTestDataBuilder.unaBicicleta().conCodigo(BIC_001).conEstado(EstadoBicicleta.ALQUILADA).build();
        Alquiler alquiler = AlquilerTestDataBuilder.unAlquiler().conBicicleta(bicicleta).build();

        when(repositorioConsulta.buscarAlquilerActivo(BIC_001)).thenReturn(Optional.of(alquiler));

        when(repositorioComando.guardar(any(Alquiler.class))).thenReturn(alquiler);

        Alquiler resultado = servicio.finalizarAlquiler(BIC_001);

        assertNotNull(resultado);
        assertEquals(EstadoBicicleta.DISPONIBLE, bicicleta.getEstadoBicicleta());
        verify(calculadorCosto).calcular(alquiler);
        verify(bicicletaComando).guardar(bicicleta);
        verify(repositorioComando).guardar(alquiler);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando no existe un alquiler activo")
    void deberiaLanzarExcepcionCuandoNoExisteAlquilerActivo() {
        when(repositorioConsulta.buscarAlquilerActivo(BIC_001)).thenReturn(Optional.empty());

        DataNotFoundException excepcion = assertThrows(DataNotFoundException.class,() -> servicio.finalizarAlquiler(BIC_001));

        assertEquals(NO_EXISTE_ALQUILER_ACTIVO + BIC_001,excepcion.getMessage());
        verify(repositorioConsulta).buscarAlquilerActivo(BIC_001);
    }
}