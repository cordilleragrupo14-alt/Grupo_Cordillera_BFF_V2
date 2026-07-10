package cl.grupocordillera.bff.controller;

import cl.grupocordillera.bff.dto.*;
import cl.grupocordillera.bff.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        return ResponseEntity.ok(ventaService.listarClientesEnmascarados());
    }

    @PostMapping
    public ResponseEntity<VentaDTO> registrar(@RequestBody VentaRegistroRequest req) {
        return ResponseEntity.ok(ventaService.registrar(req));
    }

    @GetMapping
    public ResponseEntity<HistorialVentasResponse> historial(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int pageSize) {
        return ResponseEntity.ok(ventaService.obtenerHistorial(page, pageSize));
    }
}
