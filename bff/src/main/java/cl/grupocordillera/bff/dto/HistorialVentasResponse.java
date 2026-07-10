package cl.grupocordillera.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialVentasResponse {
    private List<VentaDTO> items;
    private long total;
}
