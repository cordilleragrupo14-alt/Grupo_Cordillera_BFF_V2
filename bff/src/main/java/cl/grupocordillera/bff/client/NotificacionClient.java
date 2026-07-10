package cl.grupocordillera.bff.client;

import cl.grupocordillera.bff.dto.AnomaliaDTO;
import cl.grupocordillera.bff.dto.AnomaliaRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "notificacionClient", url = "${clients.notificaciones.url}")
public interface NotificacionClient {

    @PostMapping("/api/notificaciones/anomalias")
    AnomaliaDTO notificar(@RequestBody AnomaliaRequest request);

    @GetMapping("/api/notificaciones/anomalias")
    List<AnomaliaDTO> listar();
}
