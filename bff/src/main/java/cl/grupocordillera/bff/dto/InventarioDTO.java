package cl.grupocordillera.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDTO {
    private String sku;
    private String producto;
    private String bodega;
    private Integer stockActual;
    private Integer stockMinimo;
}
