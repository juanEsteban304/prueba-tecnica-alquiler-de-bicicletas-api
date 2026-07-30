package co.com.ceiba.alquilerbicicletas.domain.model;

public class Bicicleta {

    private String codigo;
    private EstadoBicicleta estado;
    private TipoBicicleta tipo;

    public Bicicleta(String codigo, EstadoBicicleta estado, TipoBicicleta tipo) {
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
