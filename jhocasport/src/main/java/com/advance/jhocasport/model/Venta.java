package com.advance.jhocasport.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Venta")
    private Integer id;

    @Column(name = "Fecha_Venta")
    private LocalDateTime fechaVenta;

    @ManyToOne
    @JoinColumn(name = "ID_Cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario")
    private Usuario usuario;

    @Column(name = "Total")
    private Double total;
}
