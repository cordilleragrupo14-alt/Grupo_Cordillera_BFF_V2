package cl.grupocordillera.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnomaliaRequest {
    private String modulo;
    private String severidad;
    private String mensaje;
    private Long referenciaId;
}
