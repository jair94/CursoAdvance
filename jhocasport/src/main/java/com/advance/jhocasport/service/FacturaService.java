package com.advance.jhocasport.service;

import com.advance.jhocasport.model.Factura;
import com.advance.jhocasport.repository.FacturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public List<Factura> listar() {
        return facturaRepository.findAll();
    }

    public Optional<Factura> buscarPorId(Integer id) {
        return facturaRepository.findById(id);
    }

    public Factura guardar(Factura factura) {
        return facturaRepository.save(factura);
    }

    public void eliminar(Integer id) {
        facturaRepository.deleteById(id);
    }
}
