package cl.grupocordillera.bff.service;

import cl.grupocordillera.bff.client.AnaliticaClient;
import cl.grupocordillera.bff.client.GestionClient;
import cl.grupocordillera.bff.dto.KpiFrontendDTO;
import cl.grupocordillera.bff.dto.VentaVsMetaDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ResilientDashboardGateway {

    private static final Logger log = LoggerFactory.getLogger(ResilientDashboardGateway.class);

    @Autowired
    private AnaliticaClient analiticaClient;

    @Autowired
    private GestionClient gestionClient;

    @CircuitBreaker(name = "analiticaCB", fallbackMethod = "fallbackKpis")
    public List<KpiFrontendDTO> obtenerKpisSeguro(List<String> degradedServices) {
        return analiticaClient.obtenerKpis();
    }

    public List<KpiFrontendDTO> fallbackKpis(List<String> degradedServices, Throwable t) {
        log.warn("Circuit breaker analiticaCB activado: {}", t.getMessage());
        degradedServices.add("ms-analitica-kpi");
        return Collections.emptyList();
    }

    @CircuitBreaker(name = "gestionCB", fallbackMethod = "fallbackVentasVsMetas")
    public List<VentaVsMetaDTO> obtenerVentasVsMetasSeguro(List<String> degradedServices) {
        return gestionClient.obtenerVentasVsMetas();
    }

    public List<VentaVsMetaDTO> fallbackVentasVsMetas(List<String> degradedServices, Throwable t) {
        log.warn("Circuit breaker gestionCB activado: {}", t.getMessage());
        degradedServices.add("ms-gestion");
        return Collections.emptyList();
    }
}