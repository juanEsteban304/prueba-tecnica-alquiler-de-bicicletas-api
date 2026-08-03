package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.domain.Specification;

import co.com.ceiba.alquilerbicicletas.domain.model.EstadoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.TipoBicicleta;

public class BIcicletaSpecification {

    private BIcicletaSpecification(){

    }

    @SuppressWarnings("removal")
    public static Specification<BicicletaEntidad> buscar(String codigo, EstadoBicicleta estado, TipoBicicleta tipo){
        Specification<BicicletaEntidad> specification = Specification.where(null);

        if (codigo != null && !codigo.isBlank()){
            specification = specification.and((root, query, builder) -> builder.equal(root.get("codigo"), codigo));
        }

        if (estado != null) {
            specification = specification.and((root, query, builder) -> builder.equal(root.get("estado"),estado));
        }

        if (tipo != null) {
            specification = specification.and((root, query, builder) -> builder.equal(root.get("tipo"),tipo));
        }

        return specification;
    }
    
}
