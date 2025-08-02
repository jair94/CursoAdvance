package com.advance.jhocasport.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Usuario")
    private Integer id;

    @Column(name = "Nombre_Usuario", nullable = false, unique = true)
    private String nombreUsuario;

    @Column(name = "Contraseña", nullable = false)
    private String contraseña;

    @Enumerated(EnumType.STRING)
    @Column(name = "Rol", nullable = false)
    private Rol rol;

    public enum Rol {
        admin, vendedor, facturador
    }
}
