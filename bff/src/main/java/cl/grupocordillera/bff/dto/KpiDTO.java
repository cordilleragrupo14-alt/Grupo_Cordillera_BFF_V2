package cl.grupocordillera.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KpiDTO {
    private String nombreIndicador;
    private Double valor;
    private String periodo;
}
