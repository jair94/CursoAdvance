package com.advance.jhocasport.repository;

import com.advance.jhocasport.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
