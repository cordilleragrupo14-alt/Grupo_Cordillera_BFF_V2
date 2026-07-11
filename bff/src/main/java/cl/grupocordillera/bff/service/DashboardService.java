package cl.grupocordillera.bff.service;

import cl.grupocordillera.bff.dto.DashboardConsolidadoDTO;
import cl.grupocordillera.bff.dto.KpiFrontendDTO;
import cl.grupocordillera.bff.dto.VentaVsMetaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private ResilientDashboardGateway gateway;

    public DashboardConsolidadoDTO obtenerDashboardConsolidado() {
        List<String> degradedServices = new ArrayList<>();

        List<KpiFrontendDTO> kpis = gateway.obtenerKpisSeguro(degradedServices);
        List<VentaVsMetaDTO> ventasVsMetas = gateway.obtenerVentasVsMetasSeguro(degradedServices);

        return new DashboardConsolidadoDTO(
                kpis,
                ventasVsMetas,
                !degradedServices.isEmpty(),
                degradedServices
        );
    }
}