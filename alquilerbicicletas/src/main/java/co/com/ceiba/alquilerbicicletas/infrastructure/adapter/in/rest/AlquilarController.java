package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.in.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.AlquilerComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.AlquilerFinalizacionPuerto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/alquileres")
public class AlquilarController {
    
    private final AlquilerComandoPuerto comandoPuerto;
    private final AlquilerRestMapeador mapeador;
    private final AlquilerFinalizacionPuerto finalPuerto;

    public AlquilarController(AlquilerComandoPuerto comandoPuerto, AlquilerRestMapeador mapeador, AlquilerFinalizacionPuerto finalPuerto) {
        this.comandoPuerto = comandoPuerto;
        this.mapeador = mapeador;
        this.finalPuerto = finalPuerto;
    }

    @PostMapping
    public ResponseEntity<AlquilerDTO> iniciarAlquiler(@Valid @RequestBody AlquilerDTO alquilerDTO){
        Alquiler alquiler = mapeador.convertirAEntidad(alquilerDTO);
        Alquiler alquilerRegistro = comandoPuerto.iniciarAlquiler(alquiler);
        return new ResponseEntity<>(mapeador.convertirADTO(alquilerRegistro), HttpStatus.CREATED);

    }

    @PostMapping("/{codigoBicicleta}/finalizar")
    public ResponseEntity<AlquilerDTO> finalizarAlquiler(@Valid @PathVariable String codigoBicicleta) {
    Alquiler alquilerFinalizado = finalPuerto.finalizarAlquiler(codigoBicicleta);
    return ResponseEntity.ok(mapeador.convertirADTO(alquilerFinalizado));
    }

}
