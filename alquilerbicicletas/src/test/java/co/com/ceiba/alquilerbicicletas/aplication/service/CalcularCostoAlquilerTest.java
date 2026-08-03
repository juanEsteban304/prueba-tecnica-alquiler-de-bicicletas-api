package co.com.ceiba.alquilerbicicletas.aplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import co.com.ceiba.alquilerbicicletas.application.service.CalculadorCostoAlquiler;
import co.com.ceiba.alquilerbicicletas.databuilder.AlquilerTestDataBuilder;
import co.com.ceiba.alquilerbicicletas.databuilder.BicicletaTestDataBuilder;
import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;
import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.TipoBicicleta;

class CalculadorCostoAlquilerTest {

    private CalculadorCostoAlquiler calculador = new CalculadorCostoAlquiler();

    @Test
    @DisplayName("Debe calcular costo base sin multa")
    void deberiaCalcularCostoSinMulta() {
        Bicicleta bicicleta = BicicletaTestDataBuilder.unaBicicleta().conTipo(TipoBicicleta.URBANA).build();
        Alquiler alquiler = AlquilerTestDataBuilder
                .unAlquiler()
                .conBicicleta(bicicleta)
                .conHoraInicio(LocalDateTime.of(2026, 8, 1, 10, 0))
                .conHoraFin(LocalDateTime.of(2026, 8, 1, 12, 0))
                .conDuracionEstimada(2)
                .build();

        calculador.calcular(alquiler);

        assertEquals(BigDecimal.valueOf(7000), alquiler.getCostoBase());
        assertEquals(BigDecimal.ZERO, alquiler.getMulta());
        assertEquals(BigDecimal.valueOf(7000), alquiler.getCostoTotal());
    }

    @Test
    @DisplayName("Debe calcular multa cuando el tiempo real supera la duración estimada")
    void deberiaCalcularMultaCuandoExisteRetraso() {
        Bicicleta bicicleta = BicicletaTestDataBuilder.unaBicicleta().conTipo(TipoBicicleta.URBANA).build();
        Alquiler alquiler = AlquilerTestDataBuilder
                .unAlquiler()
                .conBicicleta(bicicleta)
                .conHoraInicio(LocalDateTime.of(2026, 8, 1, 10, 0))
                .conHoraFin(LocalDateTime.of(2026, 8, 1, 13, 0))
                .conDuracionEstimada(2)
                .build();

        calculador.calcular(alquiler);

        assertEquals(0, alquiler.getCostoBase().compareTo(BigDecimal.valueOf(10500)));
        assertEquals(0, alquiler.getMulta().compareTo(BigDecimal.valueOf(1750)));
        assertEquals(0, alquiler.getCostoTotal().compareTo(BigDecimal.valueOf(12250)));
        assertEquals(3, alquiler.getTiempoRealUso());
    }

    @Test
    @DisplayName("Debe calcular tarifa diferente para bicicleta eléctrica")
    void deberiaCalcularCostoParaBicicletaElectrica() {
        Bicicleta bicicleta = BicicletaTestDataBuilder.unaBicicleta().conTipo(TipoBicicleta.ELECTRICA).build();
        Alquiler alquiler = AlquilerTestDataBuilder
                .unAlquiler()
                .conBicicleta(bicicleta)
                .conHoraInicio(LocalDateTime.of(2026, 8, 1, 8, 0))
                .conHoraFin(LocalDateTime.of(2026, 8, 1, 10, 0))
                .conDuracionEstimada(2)
                .build();

        calculador.calcular(alquiler);

        assertEquals(BigDecimal.valueOf(15000),alquiler.getCostoBase());
        assertEquals(BigDecimal.ZERO,alquiler.getMulta());
        assertEquals(BigDecimal.valueOf(15000),alquiler.getCostoTotal());
        assertEquals(2,alquiler.getTiempoRealUso());
    }
}