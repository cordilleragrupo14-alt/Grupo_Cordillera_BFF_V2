package cl.grupocordillera.bff.service;

import cl.grupocordillera.bff.client.LogisticaClient;
import cl.grupocordillera.bff.dto.DespachoDTO;
import cl.grupocordillera.bff.dto.InventarioDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class LogisticaService {

    private static final Logger log = LoggerFactory.getLogger(LogisticaService.class);

    @Autowired
    private LogisticaClient logisticaClient;

    @CircuitBreaker(name = "logisticaCB", fallbackMethod = "fallbackDespachos")
    public List<DespachoDTO> listarDespachos() {
        return logisticaClient.listarDespachos();
    }

    private List<DespachoDTO> fallbackDespachos(Throwable t) {
        log.warn("Circuit breaker logisticaCB activado en listarDespachos(): {}", t.getMessage());
        return Collections.emptyList();
    }

    @CircuitBreaker(name = "logisticaCB", fallbackMethod = "fallbackActualizarEtapa")
    public DespachoDTO actualizarEtapa(Long id, String nuevaEtapa) {
        return logisticaClient.actualizarEtapa(id, Map.of("etapa", nuevaEtapa));
    }

    private DespachoDTO fallbackActualizarEtapa(Long id, String nuevaEtapa, Throwable t) {
        log.warn("Circuit breaker logisticaCB activado en actualizarEtapa(): {}", t.getMessage());
        DespachoDTO fallback = new DespachoDTO();
        fallback.setId(id);
        fallback.setEtapa("SERVICIO_NO_DISPONIBLE");
        return fallback;
    }

    @CircuitBreaker(name = "logisticaCB", fallbackMethod = "fallbackInventario")
    public List<InventarioDTO> listarInventario() {
        return logisticaClient.listarInventario();
    }

    private List<InventarioDTO> fallbackInventario(Throwable t) {
        log.warn("Circuit breaker logisticaCB activado en listarInventario(): {}", t.getMessage());
        return Collections.emptyList();
    }
}
