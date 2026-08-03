package co.com.ceiba.alquilerbicicletas.aplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.com.ceiba.alquilerbicicletas.application.service.BicicletaConsultaServicio;
import co.com.ceiba.alquilerbicicletas.databuilder.BicicletaTestDataBuilder;
import co.com.ceiba.alquilerbicicletas.domain.exception.DataNotFoundException;
import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.EstadoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.TipoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioConsultaPuerto;

@ExtendWith(MockitoExtension.class)
public class BicicletaconsultaServicioTest {
    @Mock
    private BicicletaRepositorioConsultaPuerto repositorioConsulta;
    @InjectMocks
    private BicicletaConsultaServicio servicio;

    private static final String BIC_001 = "BIC-001";
    private static final String BIC_002 = "BIC-002";
    private static final String NO_SE_ENCONTRARON_BICICLETAS = "No se encontraron bicicletas con  código= BIC-999";
    private static final String BIC_999 = "BIC-999";

    @Test
    @DisplayName("Debe encontrar bicicletas por código")
    void debeEncontrarBicicletas_PorCodigo(){
        Bicicleta bicicleta = BicicletaTestDataBuilder.unaBicicleta().conCodigo(BIC_001).build();

        when(repositorioConsulta.buscar(BIC_001,null,null)).thenReturn(List.of(bicicleta));

        List<Bicicleta> resultado =servicio.buscar(BIC_001,null,null);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(BIC_001,resultado.get(0).getCodigo());
        verify(repositorioConsulta).buscar(BIC_001, null, null);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando no se encuentran bicicletas")
    void deberiaLanzarExcepcion_CuandoNoEncuentraBicicletas(){
        when(repositorioConsulta.buscar(BIC_999,null,null)).thenReturn(List.of());

        DataNotFoundException excepcion =assertThrows(DataNotFoundException.class,() -> servicio.buscar(BIC_999,null,null));

        assertEquals(NO_SE_ENCONTRARON_BICICLETAS,excepcion.getMessage());

        verify(repositorioConsulta).buscar(BIC_999, null, null);
    }

    @Test
    @DisplayName("Debe listar todas las bicicletas correctamente")
    void deberiaListar_TodasLasBicicletas(){
        Bicicleta bicicleta1 = BicicletaTestDataBuilder.unaBicicleta().conCodigo(BIC_001).build();
        Bicicleta bicicleta2 = BicicletaTestDataBuilder.unaBicicleta().conCodigo(BIC_002).build();
        List<Bicicleta> bicicletas = List.of(bicicleta1, bicicleta2);

        when(repositorioConsulta.listar()).thenReturn(bicicletas);

        List<Bicicleta> resultado = servicio.listar();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(BIC_001,resultado.get(0).getCodigo());
        assertEquals(BIC_002,resultado.get(1).getCodigo());
        verify(repositorioConsulta).listar();
    }

    @Test
    @DisplayName("Debe buscar bicicletas por estado correctamente")
    void deberiaBuscarBicicletas_PorEstado(){
        Bicicleta bicicleta =BicicletaTestDataBuilder.unaBicicleta().conCodigo(BIC_001).conEstado(EstadoBicicleta.DISPONIBLE).build();

        when(repositorioConsulta.buscar(null,EstadoBicicleta.DISPONIBLE,null)).thenReturn(List.of(bicicleta));

        List<Bicicleta> resultado =servicio.buscar(null,EstadoBicicleta.DISPONIBLE,null);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(EstadoBicicleta.DISPONIBLE,resultado.get(0).getEstadoBicicleta());
        verify(repositorioConsulta).buscar(null,EstadoBicicleta.DISPONIBLE,null);
    }

    @Test
    @DisplayName("Debe buscar bicicletas por tipo correctamente")
    void deberiaBuscarBicicletas_PorTipo(){
        Bicicleta bicicleta = BicicletaTestDataBuilder.unaBicicleta().conCodigo("BIC-001").conTipo(TipoBicicleta.MONTAÑA).build();

        when(repositorioConsulta.buscar(null,null,TipoBicicleta.MONTAÑA)).thenReturn(List.of(bicicleta));

        List<Bicicleta> resultado = servicio.buscar(null,null,TipoBicicleta.MONTAÑA);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(TipoBicicleta.MONTAÑA,resultado.get(0).getTipoBicicleta());
        verify(repositorioConsulta).buscar(null,null,TipoBicicleta.MONTAÑA);
    }
}
