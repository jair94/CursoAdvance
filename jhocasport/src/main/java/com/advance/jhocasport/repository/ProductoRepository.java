package com.advance.jhocasport.repository;

import com.advance.jhocasport.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
