package co.com.ceiba.alquilerbicicletas.databuilder;

import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.EstadoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.TipoBicicleta;

public class BicicletaTestDataBuilder {

     private String codigo = "BIC-001";
    private EstadoBicicleta estado = EstadoBicicleta.DISPONIBLE;
    private TipoBicicleta tipo = TipoBicicleta.URBANA;

    public BicicletaTestDataBuilder conCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public BicicletaTestDataBuilder conEstado(EstadoBicicleta estado) {
        this.estado = estado;
        return this;
    }

    public BicicletaTestDataBuilder conTipo(TipoBicicleta tipo) {
        this.tipo = tipo;
        return this;
    }

    public Bicicleta build() {
        return Bicicleta.crear(codigo, estado, tipo);
    }

    public Bicicleta buildDTO() {
        return new Bicicleta(codigo, estado, tipo);
    }

    public Bicicleta buildEntidad() {
        return new Bicicleta(codigo, estado, tipo);
    }

    public static BicicletaTestDataBuilder unaBicicleta() {
        return new BicicletaTestDataBuilder();
    }
}
    

