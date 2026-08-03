package co.com.ceiba.alquilerbicicletas.aplication.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.com.ceiba.alquilerbicicletas.application.service.BicicletaValidador;
import co.com.ceiba.alquilerbicicletas.domain.exception.CodigoException;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioConsultaPuerto;


@ExtendWith(MockitoExtension.class)
public class BicicletaValidadorTest {
    @Mock
    private BicicletaRepositorioConsultaPuerto repositorioConsulta;
    @InjectMocks
    private BicicletaValidador validador;

    private static final String BIC_001 = "BIC-001";
    private static final String CODIGO_YA_EN_USO = "El codigo de la Bicicleta ya esta en uso";

    @Test
    @DisplayName("Debe permitir el código cuando no existe")
    void deberiaPermitirCodigo_CuandoNoExiste() {
        when(repositorioConsulta.existePorCodigo(BIC_001)).thenReturn(false);

        assertDoesNotThrow(() -> validador.validarCodigoUnico(BIC_001));
        verify(repositorioConsulta).existePorCodigo(BIC_001);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el código ya está en uso")
    void deberiaLanzarExcepcion_CuandoCodigoYaExiste() {
        when(repositorioConsulta.existePorCodigo(BIC_001)).thenReturn(true);

        CodigoException excepcion = assertThrows(CodigoException.class, () -> validador.validarCodigoUnico(BIC_001));

        assertEquals(CODIGO_YA_EN_USO, excepcion.getMessage());
        verify(repositorioConsulta).existePorCodigo(BIC_001);
    }

}
