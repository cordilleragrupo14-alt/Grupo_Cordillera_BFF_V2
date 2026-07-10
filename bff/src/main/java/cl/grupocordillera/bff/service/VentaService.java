package cl.grupocordillera.bff.service;

import cl.grupocordillera.bff.client.VentaClient;
import cl.grupocordillera.bff.dto.*;
import cl.grupocordillera.bff.util.MaskUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService {

    private static final Logger log = LoggerFactory.getLogger(VentaService.class);

    @Autowired
    private VentaClient ventaClient;

    @CircuitBreaker(name = "ventasCB", fallbackMethod = "fallbackClientes")
    public List<ClienteDTO> listarClientesEnmascarados() {
        List<ClienteDTO> clientes = ventaClient.obtenerClientes();
        return clientes.stream().map(c -> new ClienteDTO(
                c.getId(),
                c.getNombre(),
                MaskUtil.maskRut(c.getRut()),
                MaskUtil.maskPhone(c.getTelefono()),
                MaskUtil.maskEmail(c.getEmail()),
                c.getRegion()
        )).collect(Collectors.toList());
    }

    private List<ClienteDTO> fallbackClientes(Throwable t) {
        log.warn("Circuit breaker ventasCB activado en listarClientes(): {}", t.getMessage());
        return Collections.emptyList();
    }

    @CircuitBreaker(name = "ventasCB", fallbackMethod = "fallbackRegistrar")
    public VentaDTO registrar(VentaRegistroRequest req) {
        VentaDTO dto = new VentaDTO();
        // TODO: mapear productos/canal según lo que espere realmente ms-ventas.
        // El contrato actual de VentaDTO (rutCliente, monto, estado) no incluye
        // productos ni canal -- hay que ajustar VentaDTO o el mapeo aquí cuando
        // se confirme el contrato real de ms-ventas.
        return ventaClient.registrar(dto);
    }

    private VentaDTO fallbackRegistrar(VentaRegistroRequest req, Throwable t) {
        log.warn("Circuit breaker ventasCB activado en registrar(): {}", t.getMessage());
        VentaDTO respuesta = new VentaDTO();
        respuesta.setEstado("SERVICIO_NO_DISPONIBLE");
        return respuesta;
    }

    @CircuitBreaker(name = "ventasCB", fallbackMethod = "fallbackHistorial")
    public HistorialVentasResponse obtenerHistorial(int page, int pageSize) {
        List<VentaDTO> items = ventaClient.listar(page, pageSize);
        return new HistorialVentasResponse(items, items.size());
    }

    private HistorialVentasResponse fallbackHistorial(int page, int pageSize, Throwable t) {
        log.warn("Circuit breaker ventasCB activado en historial(): {}", t.getMessage());
        return new HistorialVentasResponse(Collections.emptyList(), 0);
    }
}
