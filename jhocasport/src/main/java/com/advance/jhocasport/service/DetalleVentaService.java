package com.advance.jhocasport.service;

import com.advance.jhocasport.model.DetalleVenta;
import com.advance.jhocasport.repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaService {

    private final DetalleVentaRepository repository;

    public DetalleVentaService(DetalleVentaRepository repository) {
        this.repository = repository;
    }

    public List<DetalleVenta> listar() {
        return repository.findAll();
    }

    public Optional<DetalleVenta> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public DetalleVenta guardar(DetalleVenta detalle) {
        return repository.save(detalle);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
