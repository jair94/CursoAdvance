package com.advance.jhocasport.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Cliente")
    private Integer id;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "Apellido", nullable = false)
    private String apellido;

    @Column(name = "Dirección")
    private String direccion;

    @Column(name = "Teléfono")
    private String telefono;

    @Column(name = "Email")
    private String email;
}
