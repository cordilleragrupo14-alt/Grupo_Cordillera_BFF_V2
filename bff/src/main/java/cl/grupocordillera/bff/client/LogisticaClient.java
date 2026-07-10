package cl.grupocordillera.bff.client;

import cl.grupocordillera.bff.dto.DespachoDTO;
import cl.grupocordillera.bff.dto.InventarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "logisticaClient", url = "${clients.logistica.url}")
public interface LogisticaClient {

    @GetMapping("/api/logistica/despachos")
    List<DespachoDTO> listarDespachos();

    @PatchMapping("/api/logistica/despachos/{id}")
    DespachoDTO actualizarEtapa(@PathVariable("id") Long id, @RequestBody Map<String, String> body);

    @GetMapping("/api/logistica/inventario")
    List<InventarioDTO> listarInventario();
}
