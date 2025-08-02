package com.advance.jhocasport.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Movimiento")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_Producto")
    private Producto producto;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Cambio")
    private Integer cambio;

    @Column(name = "Motivo")
    private String motivo;
}
