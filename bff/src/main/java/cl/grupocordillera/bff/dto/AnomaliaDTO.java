package cl.grupocordillera.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnomaliaDTO {
    private Long id;
    private String modulo;
    private String severidad;
    private String mensaje;
    private String fecha;
    private String estado;
}
