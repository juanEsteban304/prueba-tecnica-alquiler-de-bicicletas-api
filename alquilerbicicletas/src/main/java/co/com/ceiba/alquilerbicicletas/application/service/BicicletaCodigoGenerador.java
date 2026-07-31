package co.com.ceiba.alquilerbicicletas.application.service;

public class BicicletaCodigoGenerador {
    
    private static final String BIC_001 = "BIC-001";

    public String generarSiguienteCodigo(String ultimoCodigo){
        if(ultimoCodigo == null || ultimoCodigo.isBlank()){
            return BIC_001;
        }

        String numeroTexto = ultimoCodigo.substring(4);
        int numero = Integer.parseInt(numeroTexto);
        return String.format("BIC-%03d", numero + 1);
    }
}
