package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import co.com.ceiba.alquilerbicicletas.databuilder.BicicletaTestDataBuilder;
import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.EstadoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.TipoBicicleta;

@ExtendWith(MockitoExtension.class)
public class BicicletaRepositorioAdaptadorTest {
    @Mock
    private BicicletaJpaRepositorio jpaRepositorio;
    @Mock
    private BicicletaEntidadMapeador mapeador;
    @InjectMocks
    private BicicletaRepositorioAdaptador adaptador;

    private static final String BIC_001 = "BIC-001";
    private static final String BIC_999 = "BIC-999";
    private static final String BIC_002 = "BIC-002";

    @Test
    @DisplayName("Debe guardar una bicicleta correctamente")
    void debeGuardarBicicleta_Correctamente() {
        Bicicleta bicicleta = BicicletaTestDataBuilder.unaBicicleta().conCodigo(BIC_001).build();
        BicicletaEntidad entidad = new BicicletaEntidad();

        when(mapeador.converAEntidad(bicicleta)).thenReturn(entidad);
        when(jpaRepositorio.save(entidad)).thenReturn(entidad);
        when(mapeador.convertirADTO(entidad)).thenReturn(bicicleta);

        Bicicleta resultado = adaptador.guardar(bicicleta);

        assertNotNull(resultado);
        assertEquals(BIC_001, resultado.getCodigo());
        verify(mapeador).converAEntidad(bicicleta);
        verify(jpaRepositorio).save(entidad);
        verify(mapeador).convertirADTO(entidad);
    }

    @Test
    @DisplayName("Debe validar que una bicicleta existe por código")
    void debeIndicarQueExisteBicicletaPorCodigo() {
        when(jpaRepositorio.existsByCodigo(BIC_001)).thenReturn(true);

        boolean resultado = adaptador.existePorCodigo(BIC_001);

        assertEquals(true, resultado);
        verify(jpaRepositorio).existsByCodigo(BIC_001);
    }

    @Test
    @DisplayName("Debe encontrar bicicleta por código")
    void debeEncontrarBicicleta_PorCodigo() {
        BicicletaEntidad entidad = new BicicletaEntidad();
        Bicicleta bicicleta = BicicletaTestDataBuilder.unaBicicleta().conCodigo(BIC_001).build();

        when(jpaRepositorio.findById(BIC_001)).thenReturn(Optional.of(entidad));
        when(mapeador.convertirADTO(entidad)).thenReturn(bicicleta);

        Optional<Bicicleta> resultado = adaptador.buscarPorCodigo(BIC_001);

        assertEquals(true, resultado.isPresent());
        assertEquals(BIC_001, resultado.get().getCodigo());
        verify(jpaRepositorio).findById(BIC_001);
        verify(mapeador).convertirADTO(entidad);
    }

    @Test
    @DisplayName("No debe encontrar bicicleta cuando el código no existe")
    void noDeberiaEncontrarBicicletaCuandoCodigoNoExiste() {
        when(jpaRepositorio.findById(BIC_999)).thenReturn(Optional.empty());

        Optional<Bicicleta> resultado = adaptador.buscarPorCodigo(BIC_999);

        assertEquals(false, resultado.isPresent());
        verify(jpaRepositorio).findById(BIC_999);
        verify(mapeador, never()).convertirADTO(any(BicicletaEntidad.class));
    }

    @Test
    @DisplayName("Debe encontrar la última bicicleta registrada")
    void deberiaEncontrarUltimaBicicleta() {
        BicicletaEntidad entidad = new BicicletaEntidad();
        Bicicleta bicicleta = BicicletaTestDataBuilder.unaBicicleta().conCodigo(BIC_002).build();

        when(jpaRepositorio.findTopByOrderByCodigoDesc()).thenReturn(Optional.of(entidad));
        when(mapeador.convertirADTO(entidad)).thenReturn(bicicleta);

        Optional<Bicicleta> resultado = adaptador.buscarUltimaBicicleta();

        assertEquals(true, resultado.isPresent());
        assertEquals(BIC_002, resultado.get().getCodigo());
        verify(jpaRepositorio).findTopByOrderByCodigoDesc();
        verify(mapeador).convertirADTO(entidad);
    }

    @Test
    @DisplayName("No debe encontrar última bicicleta cuando no existen registros")
    void noDeberiaEncontrarUltimaBicicletaCuandoNoExistenRegistros() {
        when(jpaRepositorio.findTopByOrderByCodigoDesc()).thenReturn(Optional.empty());

        Optional<Bicicleta> resultado = adaptador.buscarUltimaBicicleta();

        assertEquals(false, resultado.isPresent());
        verify(jpaRepositorio).findTopByOrderByCodigoDesc();
        verify(mapeador, never()).convertirADTO(any(BicicletaEntidad.class));
    }

    @Test
    @DisplayName("Debe listar todas las bicicletas correctamente")
    void deberiaListarTodasLasBicicletas() {
        BicicletaEntidad entidad1 = new BicicletaEntidad();
        BicicletaEntidad entidad2 = new BicicletaEntidad();
        Bicicleta bicicleta1 = BicicletaTestDataBuilder.unaBicicleta().conCodigo(BIC_001).build();
        Bicicleta bicicleta2 = BicicletaTestDataBuilder.unaBicicleta().conCodigo(BIC_002).build();

        when(jpaRepositorio.findAll()).thenReturn(List.of(entidad1, entidad2));
        when(mapeador.convertirADTO(entidad1)).thenReturn(bicicleta1);
        when(mapeador.convertirADTO(entidad2)).thenReturn(bicicleta2);

        List<Bicicleta> resultado = adaptador.listar();

        assertEquals(2, resultado.size());
        assertEquals(BIC_001, resultado.get(0).getCodigo());
        assertEquals(BIC_002, resultado.get(1).getCodigo());
        verify(jpaRepositorio).findAll();
        verify(mapeador).convertirADTO(entidad1);
        verify(mapeador).convertirADTO(entidad2);
    }

    @SuppressWarnings("unchecked")
    @Test
    @DisplayName("Debe buscar bicicletas aplicando filtros correctamente")
    void deberiaBuscarBicicletasConFiltros() {
        BicicletaEntidad entidad = new BicicletaEntidad();
        Bicicleta bicicleta = BicicletaTestDataBuilder.unaBicicleta().conCodigo(BIC_001).build();

        when(jpaRepositorio.findAll(any(Specification.class))).thenReturn(List.of(entidad));
        when(mapeador.convertirADTO(entidad)).thenReturn(bicicleta);

        List<Bicicleta> resultado = adaptador.buscar(BIC_001,EstadoBicicleta.DISPONIBLE,TipoBicicleta.URBANA);

        assertEquals(1, resultado.size());
        assertEquals(BIC_001,resultado.get(0).getCodigo());
        verify(jpaRepositorio).findAll(any(Specification.class));
        verify(mapeador).convertirADTO(entidad);
    }
}
