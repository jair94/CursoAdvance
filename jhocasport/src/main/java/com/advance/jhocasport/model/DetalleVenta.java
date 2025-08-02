package com.advance.jhocasport.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalles_venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Detalle")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_Venta")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "ID_Producto")
    private Producto producto;

    @Column(name = "Cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "Precio_Subtotal", nullable = false)
    private Double precioSubtotal;

    @Column(name = "Descuento")
    private Double descuento;
}
