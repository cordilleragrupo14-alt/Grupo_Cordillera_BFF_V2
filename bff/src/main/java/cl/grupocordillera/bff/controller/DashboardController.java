package cl.grupocordillera.bff.controller;

import cl.grupocordillera.bff.dto.DashboardConsolidadoDTO;
import cl.grupocordillera.bff.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gerencia")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard-consolidado")
    public ResponseEntity<DashboardConsolidadoDTO> obtenerDashboard() {
        return ResponseEntity.ok(dashboardService.obtenerDashboardConsolidado());
    }
}
