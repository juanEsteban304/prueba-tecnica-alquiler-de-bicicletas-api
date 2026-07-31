package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BicicletaJpaRepositorio extends JpaRepository<BicicletaEntidad, String>, JpaSpecificationExecutor<BicicletaEntidad>{

    boolean existsByCodigo(String codigo);
    Optional<BicicletaEntidad> findTopByOrderByCodigoDesc();
}
