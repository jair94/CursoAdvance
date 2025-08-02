package com.advance.jhocasport.controller;

import com.advance.jhocasport.model.Venta;
import com.advance.jhocasport.service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    private final VentaService service;

    public VentaController(VentaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Venta> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscar(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Venta guardar(@RequestBody Venta venta) {
        return service.guardar(venta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(@PathVariable Integer id, @RequestBody Venta v) {
        return service.buscarPorId(id).map(actual -> {
            actual.setCliente(v.getCliente());
            actual.setUsuario(v.getUsuario());
            actual.setFechaVenta(v.getFechaVenta());
            actual.setTotal(v.getTotal());
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
