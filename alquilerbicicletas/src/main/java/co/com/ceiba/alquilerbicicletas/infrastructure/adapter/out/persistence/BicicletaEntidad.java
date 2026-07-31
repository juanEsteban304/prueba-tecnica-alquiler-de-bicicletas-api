package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.out.persistence;

import co.com.ceiba.alquilerbicicletas.domain.model.EstadoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.TipoBicicleta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Registro_Bicicletas")
public class BicicletaEntidad {

    @Id
    @Column(nullable = false)
    private String codigo;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoBicicleta estado;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoBicicleta tipo;

    public BicicletaEntidad(){
    }

    public BicicletaEntidad(String codigo, EstadoBicicleta estado, TipoBicicleta tipo) {
        this.codigo = codigo;
        this.estado = estado;
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public EstadoBicicleta getEstadoBicicleta() {
        return estado;
    }

    public void setEstadoBicicleta(EstadoBicicleta estado) {
        this.estado = estado;
    }

    public TipoBicicleta getTipoBicicleta() {
        return tipo;
    }

    public void setTipoBicicleta(TipoBicicleta tipo) {
        this.tipo = tipo;
    }


}
