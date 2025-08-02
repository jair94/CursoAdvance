package com.advance.jhocasport.controller;

import com.advance.jhocasport.model.MovimientoInventario;
import com.advance.jhocasport.service.MovimientoInventarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@CrossOrigin(origins = "*")
public class MovimientoInventarioController {

    private final MovimientoInventarioService service;

    public MovimientoInventarioController(MovimientoInventarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<MovimientoInventario> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoInventario> buscar(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MovimientoInventario guardar(@RequestBody MovimientoInventario m) {
        return service.guardar(m);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoInventario> actualizar(@PathVariable Integer id, @RequestBody MovimientoInventario m) {
        return service.buscarPorId(id).map(actual -> {
            actual.setProducto(m.getProducto());
            actual.setFecha(m.getFecha());
            actual.setCambio(m.getCambio());
            actual.setMotivo(m.getMotivo());
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
