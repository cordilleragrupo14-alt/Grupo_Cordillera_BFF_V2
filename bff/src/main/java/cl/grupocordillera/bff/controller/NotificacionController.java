package cl.grupocordillera.bff.controller;

import cl.grupocordillera.bff.dto.AnomaliaDTO;
import cl.grupocordillera.bff.dto.AnomaliaRequest;
import cl.grupocordillera.bff.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @PostMapping("/anomalias")
    public ResponseEntity<AnomaliaDTO> notificar(@RequestBody AnomaliaRequest req) {
        return ResponseEntity.ok(notificacionService.notificarAnomalia(req));
    }

    @GetMapping("/anomalias")
    public ResponseEntity<List<AnomaliaDTO>> listar() {
        return ResponseEntity.ok(notificacionService.listarAnomalias());
    }
}
