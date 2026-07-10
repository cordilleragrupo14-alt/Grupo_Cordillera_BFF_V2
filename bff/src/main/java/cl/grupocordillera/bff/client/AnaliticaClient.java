package cl.grupocordillera.bff.client;

import cl.grupocordillera.bff.dto.KpiFrontendDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "analiticaClient", url = "${clients.analitica.url}")
public interface AnaliticaClient {

    @GetMapping("/api/analitica/kpis")
    List<KpiFrontendDTO> obtenerKpis();
}
