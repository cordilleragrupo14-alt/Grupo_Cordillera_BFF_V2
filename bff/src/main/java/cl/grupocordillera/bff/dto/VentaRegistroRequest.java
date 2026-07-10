package cl.grupocordillera.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaRegistroRequest {
    private Long clienteId;
    private List<ProductoVentaDTO> productos;
    private String canal;
}
