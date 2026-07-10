package cl.grupocordillera.bff.controller;

import cl.grupocordillera.bff.dto.DespachoDTO;
import cl.grupocordillera.bff.dto.InventarioDTO;
import cl.grupocordillera.bff.service.LogisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/logistica")
public class LogisticaController {

    @Autowired
    private LogisticaService logisticaService;

    @GetMapping("/despachos")
    public ResponseEntity<List<DespachoDTO>> listarDespachos() {
        return ResponseEntity.ok(logisticaService.listarDespachos());
    }

    @PatchMapping("/despachos/{id}")
    public ResponseEntity<DespachoDTO> actualizarEtapa(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(logisticaService.actualizarEtapa(id, body.get("etapa")));
    }

    @GetMapping("/inventario")
    public ResponseEntity<List<InventarioDTO>> listarInventario() {
        return ResponseEntity.ok(logisticaService.listarInventario());
    }
}
