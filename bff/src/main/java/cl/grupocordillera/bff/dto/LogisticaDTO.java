package cl.grupocordillera.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogisticaDTO {
    private Long id;
    private String tipoEnvio;
    private String estadoDespacho;
    private String direccionDestino;
}
