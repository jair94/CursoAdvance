package com.advance.jhocasport.controller;

import com.advance.jhocasport.model.DetalleVenta;
import com.advance.jhocasport.service.DetalleVentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-venta")
@CrossOrigin(origins = "*")
public class DetalleVentaController {

    private final DetalleVentaService service;

    public DetalleVentaController(DetalleVentaService service) {
        this.service = service;
    }

    @GetMapping
    public List<DetalleVenta> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> buscar(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetalleVenta guardar(@RequestBody DetalleVenta d) {
        return service.guardar(d);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVenta> actualizar(@PathVariable Integer id, @RequestBody DetalleVenta d) {
        return service.buscarPorId(id).map(actual -> {
            actual.setVenta(d.getVenta());
            actual.setProducto(d.getProducto());
            actual.setCantidad(d.getCantidad());
            actual.setPrecioSubtotal(d.getPrecioSubtotal());
            actual.setDescuento(d.getDescuento());
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

