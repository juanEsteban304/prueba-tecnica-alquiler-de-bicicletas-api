package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.in.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.BicicletaComandoPuerto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bicicletas")
public class BicicletaController {
    
    private final BicicletaComandoPuerto comandoPuerto;
    private final BicicletaRestMapeador mapeador;

    public BicicletaController(BicicletaComandoPuerto comandoPuerto, BicicletaRestMapeador mapeador) {
        this.comandoPuerto = comandoPuerto;
        this.mapeador = mapeador;
    }

    @PostMapping
    public ResponseEntity<BicicletaDTO> registrarBicicleta(@Valid @RequestBody BicicletaDTO bicicletaDTO){
        Bicicleta registrada = comandoPuerto.registrarBicicleta(mapeador.convertirAEntidad(bicicletaDTO));
        return new ResponseEntity<>(mapeador.convertirADTO(registrada), HttpStatus.CREATED);
    }
}
