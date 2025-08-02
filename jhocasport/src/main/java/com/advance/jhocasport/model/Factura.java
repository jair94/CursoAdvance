package com.advance.jhocasport.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "facturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Factura")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "ID_Venta", unique = true)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario")
    private Usuario usuario;

    @Column(name = "Fecha_Factura")
    private LocalDateTime fechaFactura;

    @Column(name = "Monto_Total", nullable = false)
    private Double montoTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "Tipo_Pago", nullable = false)
    private TipoPago tipoPago;

    @Enumerated(EnumType.STRING)
    @Column(name = "Estado")
    private EstadoFactura estado;

    public enum TipoPago {
        efectivo, tarjeta, transferencia
    }

    public enum EstadoFactura {
        emitida, pagada, anulada
    }
}
