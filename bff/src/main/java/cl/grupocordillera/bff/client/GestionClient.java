package cl.grupocordillera.bff.client;

import cl.grupocordillera.bff.dto.VentaVsMetaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "gestionClient", url = "${clients.gestion.url}")
public interface GestionClient {

    @GetMapping("/api/gestion/ventas-vs-metas")
    List<VentaVsMetaDTO> obtenerVentasVsMetas();
}
