package cl.grupocordillera.bff.service;

import cl.grupocordillera.bff.client.AnaliticaClient;
import cl.grupocordillera.bff.client.GestionClient;
import cl.grupocordillera.bff.dto.DashboardConsolidadoDTO;
import cl.grupocordillera.bff.dto.KpiFrontendDTO;
import cl.grupocordillera.bff.dto.VentaVsMetaDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DashboardService {

    private static final Logger log = LoggerFactory.getLogger(DashboardService.class);

    @Autowired
    private AnaliticaClient analiticaClient;

    @Autowired
    private GestionClient gestionClient;

    public DashboardConsolidadoDTO obtenerDashboardConsolidado() {
        List<String> degradedServices = new ArrayList<>();

        List<KpiFrontendDTO> kpis = obtenerKpisSeguro(degradedServices);
        List<VentaVsMetaDTO> ventasVsMetas = obtenerVentasVsMetasSeguro(degradedServices);

        return new DashboardConsolidadoDTO(
                kpis,
                ventasVsMetas,
                !degradedServices.isEmpty(),
                degradedServices
        );
    }

    @CircuitBreaker(name = "analiticaCB", fallbackMethod = "fallbackKpis")
    public List<KpiFrontendDTO> obtenerKpisSeguro(List<String> degradedServices) {
        return analiticaClient.obtenerKpis();
    }

    private List<KpiFrontendDTO> fallbackKpis(List<String> degradedServices, Throwable t) {
        log.warn("Circuit breaker analiticaCB activado: {}", t.getMessage());
        degradedServices.add("ms-analitica-kpi");
        return Collections.emptyList();
    }

    @CircuitBreaker(name = "gestionCB", fallbackMethod = "fallbackVentasVsMetas")
    public List<VentaVsMetaDTO> obtenerVentasVsMetasSeguro(List<String> degradedServices) {
        return gestionClient.obtenerVentasVsMetas();
    }

    private List<VentaVsMetaDTO> fallbackVentasVsMetas(List<String> degradedServices, Throwable t) {
        log.warn("Circuit breaker gestionCB activado: {}", t.getMessage());
        degradedServices.add("ms-gestion");
        return Collections.emptyList();
    }
}
