package co.com.ceiba.alquilerbicicletas.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.alquilerbicicletas.application.service.BicicletaCodigoGenerador;
import co.com.ceiba.alquilerbicicletas.application.service.BicicletaComandoServicio;
import co.com.ceiba.alquilerbicicletas.application.service.BicicletaValidador;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.BicicletaComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.out.BicicletaRepositorioConsultaPuerto;

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
}
