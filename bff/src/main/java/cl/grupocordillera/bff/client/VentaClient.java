package cl.grupocordillera.bff.client;

import cl.grupocordillera.bff.dto.ClienteDTO;
import cl.grupocordillera.bff.dto.VentaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ventaClient", url = "${clients.ventas.url}")
public interface VentaClient {

    @GetMapping("/api/ventas/clientes")
    List<ClienteDTO> obtenerClientes();

    @PostMapping("/api/ventas")
    VentaDTO registrar(@RequestBody VentaDTO dto);

    @GetMapping("/api/ventas")
    List<VentaDTO> listar(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize);

    @GetMapping("/api/ventas/{id}")
    VentaDTO obtenerPorId(@PathVariable("id") Long id);
}
