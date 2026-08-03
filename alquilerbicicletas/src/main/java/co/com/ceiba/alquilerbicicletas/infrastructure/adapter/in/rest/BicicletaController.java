package co.com.ceiba.alquilerbicicletas.infrastructure.adapter.in.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.alquilerbicicletas.domain.model.Bicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.EstadoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.model.TipoBicicleta;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.BicicletaComandoPuerto;
import co.com.ceiba.alquilerbicicletas.domain.ports.in.BicicletaConsultaPuerto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bicicletas")
public class BicicletaController {
    
    private final BicicletaComandoPuerto comandoPuerto;
    private final BicicletaRestMapeador mapeador;
    private final BicicletaConsultaPuerto consultaPuerto;


    public BicicletaController(BicicletaComandoPuerto comandoPuerto, BicicletaRestMapeador mapeador, BicicletaConsultaPuerto consultaPuerto) {
        this.comandoPuerto = comandoPuerto;
        this.mapeador = mapeador;
        this.consultaPuerto = consultaPuerto;
    }

    @PostMapping
    public ResponseEntity<BicicletaDTO> registrarBicicleta(@Valid @RequestBody BicicletaDTO bicicletaDTO){
        Bicicleta registrada = comandoPuerto.registrarBicicleta(mapeador.convertirAEntidad(bicicletaDTO));
        return new ResponseEntity<>(mapeador.convertirADTO(registrada), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BicicletaDTO>> listarBicicletas(){
        List<Bicicleta> bicicletas = consultaPuerto.listar();
        return ResponseEntity.ok(mapeador.convertirADTO(bicicletas));
    }

    @GetMapping("/busqueda")
    public ResponseEntity<List<BicicletaDTO>> buscarBicicletas(
        @RequestParam(required = false) String codigo,
        @RequestParam(required = false) EstadoBicicleta estado,
        @RequestParam(required = false) TipoBicicleta tipo){
        List<Bicicleta> bicicletas = consultaPuerto.buscar(codigo, estado, tipo);
        return ResponseEntity.ok(mapeador.convertirADTO(bicicletas));
    }

    @PatchMapping("/{codigo}/estado")
    public ResponseEntity<BicicletaDTO> cambiarEstado(@PathVariable String codigo, @Valid @RequestBody EstadoBicicletaDTO dto) {
    Bicicleta bicicleta =comandoPuerto.cambiarEstado(codigo,mapeador.convertirEstado(dto));
    return ResponseEntity.ok(mapeador.convertirADTO(bicicleta));
    }
}
