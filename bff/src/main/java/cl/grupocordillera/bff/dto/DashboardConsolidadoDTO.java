package cl.grupocordillera.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardConsolidadoDTO {
    private List<KpiFrontendDTO> kpis;
    private List<VentaVsMetaDTO> ventasVsMetas;
    private boolean degraded;
    private List<String> degradedServices;
}
