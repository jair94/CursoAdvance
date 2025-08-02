package com.advance.jhocasport.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Producto")
    private Integer id;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "Descripción")
    private String descripcion;

    @Column(name = "Precio", nullable = false)
    private Double precio;

    @Column(name = "Stock", nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "ID_Categoria")
    private Categoria categoria;
}
