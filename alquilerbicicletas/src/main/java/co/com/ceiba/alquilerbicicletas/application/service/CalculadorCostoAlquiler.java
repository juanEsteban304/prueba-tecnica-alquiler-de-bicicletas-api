package co.com.ceiba.alquilerbicicletas.application.service;

import java.math.BigDecimal;
import java.time.Duration;

import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;
import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;

public class CalculadorCostoAlquiler {
    private static final String TIPO_INVALIDO = "El estado o tipo de DATO enviado no es válido";

    private long calcularHorasReales(Alquiler alquiler){
        Duration tiempoReal = Duration.between(
            alquiler.getHoraInicio(),
            alquiler.getHoraFin());

        long horas = tiempoReal.toHours();
        long minutos = tiempoReal.toMinutesPart();

        if (minutos > 0){
            horas++;
        }
        return horas;
    }

    private BigDecimal obtenerTarifa(Bicicleta bicicleta){
        switch (bicicleta.getTipoBicicleta()) {
            case URBANA:
                return BigDecimal.valueOf(3500);
            case MONTAÑA:
                return BigDecimal.valueOf(5000);
            case ELECTRICA:
                return BigDecimal.valueOf(7500);
            default:
                throw new IllegalArgumentException(TIPO_INVALIDO);
        }
    }

    private BigDecimal calcularCostoBase(long horasReales, BigDecimal tarifa){
        return tarifa.multiply(BigDecimal.valueOf(horasReales));
    }

    private long calcularHorasRetraso(Alquiler alquiler){
        long horasReales = calcularHorasReales(alquiler);
        long horasRetraso = horasReales - alquiler.getDuracionEstimada();
        return Math.max(horasRetraso, 0);
    }

    private BigDecimal calcularMulta(long horasRetraso, BigDecimal tarifa){
        if (horasRetraso == 0){
            return BigDecimal.ZERO;
        }
        BigDecimal mediaTarifa = tarifa.multiply(BigDecimal.valueOf(0.5));
        return mediaTarifa.multiply(BigDecimal.valueOf(horasRetraso));
    }

    private BigDecimal calcularCostoTotal(BigDecimal costoBase, BigDecimal multa){
        return costoBase.add(multa);
    }

    public void calcular(Alquiler alquiler){
        long horasReales = calcularHorasReales(alquiler);
        alquiler.setTiempoRealUso((int)horasReales);
        BigDecimal tarifa = obtenerTarifa(alquiler.getBicicleta());
        BigDecimal costoBase = calcularCostoBase(horasReales, tarifa);
        long horasRetraso = calcularHorasRetraso(alquiler);
        BigDecimal multa = calcularMulta(horasRetraso, tarifa);
        BigDecimal costoTotal = calcularCostoTotal(costoBase, multa);

        alquiler.setCostoBase(costoBase);
        alquiler.setMulta(multa);
        alquiler.setCostoTotal(costoTotal); 
    }
}
