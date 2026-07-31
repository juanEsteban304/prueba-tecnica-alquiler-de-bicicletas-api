package co.com.ceiba.alquilerbicicletas.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.alquilerbicicletas.application.service.AlquilerComandoServicio;
import co.com.ceiba.alquilerbicicletas.application.service.BicicletaCodigoGenerador;
import co.com.ceiba.alquilerbicicletas.application.service.BicicletaComandoServicio;
import co.com.ceiba.alquilerbicicletas.application.service.BicicletaValidador;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.AlquilerComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.BicicletaComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.AlquilerRepositorioComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioConsultaPuerto;
import co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence.AlquilerEntidadMapeador;
import co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence.AlquilerJpaRepositorio;
import co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence.AlquilerRepositorioAdaptador;
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
    public AlquilerRepositorioAdaptador alquilerRepositorioAdaptador(
        AlquilerJpaRepositorio jpaRepositorio, 
        AlquilerEntidadMapeador entidadMapeador,
        BicicletaJpaRepositorio bicicletaJpaRepositorio){
            return new AlquilerRepositorioAdaptador(jpaRepositorio, entidadMapeador, bicicletaJpaRepositorio);
        }

    @Bean
    public AlquilerComandoPuerto alquilerComandoPuerto(
        BicicletaRepositorioConsultaPuerto repositorioConsulta,
        BicicletaRepositorioComandoPuerto repositorioComando,
        AlquilerRepositorioComandoPuerto alquilerComando) {
            return new AlquilerComandoServicio(repositorioConsulta,repositorioComando,alquilerComando);
        }
}
