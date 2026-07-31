package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlquilerJpaRepositorio extends JpaRepository<AlquilerEntidad, Long>{

    Optional<AlquilerEntidad> findByBicicleta_CodigoAndHoraFinIsNull(String codigoBicicleta);
}
