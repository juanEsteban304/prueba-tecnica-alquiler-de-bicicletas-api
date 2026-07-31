package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlquilerJpaRepositorio extends JpaRepository<AlquilerEntidad, Long>{

}
