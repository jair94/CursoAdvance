package com.advance.jhocasport.service;

import com.advance.jhocasport.model.MovimientoInventario;
import com.advance.jhocasport.repository.MovimientoInventarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoInventarioService {

    private final MovimientoInventarioRepository repository;

    public MovimientoInventarioService(MovimientoInventarioRepository repository) {
        this.repository = repository;
    }

    public List<MovimientoInventario> listar() {
        return repository.findAll();
    }

    public Optional<MovimientoInventario> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public MovimientoInventario guardar(MovimientoInventario movimiento) {
        return repository.save(movimiento);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
