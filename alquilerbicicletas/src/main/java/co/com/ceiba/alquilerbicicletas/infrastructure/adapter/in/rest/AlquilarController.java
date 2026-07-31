package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.in.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.alquilerbicicletas.domain.model.Alquiler;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.AlquilerComandoPuerto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/alquileres")
public class AlquilarController {
    
    private final AlquilerComandoPuerto comandoPuerto;
    private final AlquilerRestMapeador mapeador;

    public AlquilarController(AlquilerComandoPuerto comandoPuerto, AlquilerRestMapeador mapeador) {
        this.comandoPuerto = comandoPuerto;
        this.mapeador = mapeador;
    }

    @PostMapping
    public ResponseEntity<AlquilerDTO> iniciarAlquiler(@Valid @RequestBody AlquilerDTO alquilerDTO){
        Alquiler alquiler = mapeador.convertirAEntidad(alquilerDTO);
        Alquiler alquilerRegistro = comandoPuerto.iniciarAlquiler(alquiler);
        return new ResponseEntity<>(mapeador.convertirADTO(alquilerRegistro), HttpStatus.CREATED);

    }

}
