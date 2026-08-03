package co.com.ceiba.alquilerbicicletas.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.alquilerbicicletas.application.service.AlquilerComandoServicio;
import co.com.ceiba.alquilerbicicletas.application.service.AlquilerConsultaServicio;
import co.com.ceiba.alquilerbicicletas.application.service.AlquilerFinalizacionServicio;
import co.com.ceiba.alquilerbicicletas.application.service.BicicletaCodigoGenerador;
import co.com.ceiba.alquilerbicicletas.application.service.BicicletaComandoServicio;
import co.com.ceiba.alquilerbicicletas.application.service.BicicletaConsultaServicio;
import co.com.ceiba.alquilerbicicletas.application.service.BicicletaValidador;
import co.com.ceiba.alquilerbicicletas.application.service.CalculadorCostoAlquiler;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.AlquilerComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.AlquilerConsultaPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.AlquilerFinalizacionPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.BicicletaComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.BicicletaConsultaPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.AlquilerRepositorioComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.AlquilerRepositorioConsultaPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioConsultaPuerto;
import co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence.AlquilerEntidadMapeador;
import co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence.AlquilerJpaRepositorio;
import co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence.AlquilerMapeador;
import co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence.AlquilerRepositorioAdaptador;
import co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence.AlquilerRepositorioConsultaAdaptador;
import co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence.BicicletaJpaRepositorio;

@Configuration
public class BicicletaBeanConfiguration {
    
    @Bean
    public BicicletaValidador bicicletaValidador(BicicletaRepositorioConsultaPuerto repositorioConsulta){
        return new BicicletaValidador(repositorioConsulta);
    }

    @Bean
    public BicicletaComandoPuerto bicicletaComandoPuerto(
        BicicletaRepositorioComandoPuerto repositorioComando,
        BicicletaValidador bicicletaValidador,
        BicicletaRepositorioConsultaPuerto repositorioConsulta,
        BicicletaCodigoGenerador generador){
            return new BicicletaComandoServicio(repositorioComando, bicicletaValidador,repositorioConsulta, generador);
        }

    @Bean
    public BicicletaCodigoGenerador generador(){
        return new BicicletaCodigoGenerador();
    }

    @Bean
    public AlquilerRepositorioComandoPuerto alquilerRepositorioComandoPuerto(
        AlquilerJpaRepositorio jpaRepositorio, 
        AlquilerEntidadMapeador entidadMapeador,
        BicicletaJpaRepositorio bicicletaJpaRepositorio,
        AlquilerMapeador alquilerMapeador){
            return new AlquilerRepositorioAdaptador(jpaRepositorio, entidadMapeador, bicicletaJpaRepositorio,alquilerMapeador);
        }

    @Bean
    public AlquilerComandoPuerto alquilerComandoPuerto(
        BicicletaRepositorioConsultaPuerto repositorioConsulta,
        BicicletaRepositorioComandoPuerto repositorioComando,
        AlquilerRepositorioComandoPuerto alquilerComando) {
            return new AlquilerComandoServicio(repositorioConsulta,repositorioComando,alquilerComando);
        }
    
    @Bean
    public AlquilerRepositorioConsultaPuerto alquilerRepositorioConsultaPuerto(
            AlquilerJpaRepositorio jpaRepositorio,
            AlquilerEntidadMapeador entidadMapeador) {

        return new AlquilerRepositorioConsultaAdaptador(jpaRepositorio, entidadMapeador);
    }

    @Bean
    public AlquilerMapeador alquilerMapeador() {
        return new AlquilerMapeador();
    }

    @Bean
    public AlquilerFinalizacionPuerto finalizarAlquiler(
            AlquilerRepositorioConsultaPuerto repositorioConsulta,
            AlquilerRepositorioComandoPuerto repositorioComando,
            BicicletaRepositorioComandoPuerto bicicletaRepositorioComando,
            CalculadorCostoAlquiler calculadorCosto) {

        return new AlquilerFinalizacionServicio(repositorioConsulta,repositorioComando,bicicletaRepositorioComando,calculadorCosto);
    }

    @Bean
    public CalculadorCostoAlquiler calculadorCostoAlquiler() {
        return new CalculadorCostoAlquiler();
    }

    @Bean
    public BicicletaConsultaPuerto bicicletaConsultaPuerto(BicicletaRepositorioConsultaPuerto repositorioConsulta) {
        return new BicicletaConsultaServicio(repositorioConsulta);
    }

    @Bean
    public AlquilerConsultaPuerto alquilerConsultaPuerto(AlquilerRepositorioConsultaPuerto repositorioConsulta) {
        return new AlquilerConsultaServicio(repositorioConsulta);
    }
}
 