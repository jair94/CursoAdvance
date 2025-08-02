package com.advance.jhocasport.service;

import com.advance.jhocasport.model.Venta;
import com.advance.jhocasport.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<Venta> listar() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> buscarPorId(Integer id) {
        return ventaRepository.findById(id);
    }

    public Venta guardar(Venta venta) {
        return ventaRepository.save(venta);
    }

    public void eliminar(Integer id) {
        ventaRepository.deleteById(id);
    }
}
