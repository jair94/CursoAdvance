package com.advance.jhocasport.controller;

import com.advance.jhocasport.model.Factura;
import com.advance.jhocasport.service.FacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@CrossOrigin(origins = "*")
public class FacturaController {

    private final FacturaService service;

    public FacturaController(FacturaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Factura> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> buscar(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Factura guardar(@RequestBody Factura factura) {
        return service.guardar(factura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> actualizar(@PathVariable Integer id, @RequestBody Factura f) {
        return service.buscarPorId(id).map(actual -> {
            actual.setVenta(f.getVenta());
            actual.setUsuario(f.getUsuario());
            actual.setFechaFactura(f.getFechaFactura());
            actual.setMontoTotal(f.getMontoTotal());
            actual.setTipoPago(f.getTipoPago());
            actual.setEstado(f.getEstado());
            return ResponseEntity.ok(service.guardar(actual));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (service.buscarPorId(id).isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
