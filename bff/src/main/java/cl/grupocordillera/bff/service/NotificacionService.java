package cl.grupocordillera.bff.service;

import cl.grupocordillera.bff.client.NotificacionClient;
import cl.grupocordillera.bff.dto.AnomaliaDTO;
import cl.grupocordillera.bff.dto.AnomaliaRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NotificacionService {

    private static final Logger log = LoggerFactory.getLogger(NotificacionService.class);

    @Autowired
    private NotificacionClient notificacionClient;

    @CircuitBreaker(name = "notificacionesCB", fallbackMethod = "fallbackNotificar")
    public AnomaliaDTO notificarAnomalia(AnomaliaRequest req) {
        return notificacionClient.notificar(req);
    }

    private AnomaliaDTO fallbackNotificar(AnomaliaRequest req, Throwable t) {
        log.warn("Circuit breaker notificacionesCB activado en notificar(): {}", t.getMessage());
        AnomaliaDTO respuesta = new AnomaliaDTO();
        respuesta.setEstado("ENCOLADO");
        return respuesta;
    }

    @CircuitBreaker(name = "notificacionesCB", fallbackMethod = "fallbackListar")
    public List<AnomaliaDTO> listarAnomalias() {
        return notificacionClient.listar();
    }

    private List<AnomaliaDTO> fallbackListar(Throwable t) {
        log.warn("Circuit breaker notificacionesCB activado en listar(): {}", t.getMessage());
        return Collections.emptyList();
    }
}
