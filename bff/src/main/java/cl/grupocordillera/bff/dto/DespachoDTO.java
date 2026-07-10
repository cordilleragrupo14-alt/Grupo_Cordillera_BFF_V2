package cl.grupocordillera.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DespachoDTO {
    private Long id;
    private String cliente;
    private String destino;
    private String etapa;
    private boolean atraso;
    private String fechaEstimada;
}
