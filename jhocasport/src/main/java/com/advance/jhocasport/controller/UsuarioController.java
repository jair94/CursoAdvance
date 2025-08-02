package com.advance.jhocasport.controller;

import com.advance.jhocasport.model.Usuario;
import com.advance.jhocasport.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
   private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
          this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listar(){
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Integer id) {
        return usuarioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

    }

    @GetMapping("/nombre/{nombreUsuario}")
    public Usuario buscarPorNombreUsuario(@PathVariable String nombreUsuario) {
        return usuarioService.buscarPorNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con nombre: " + nombreUsuario));
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return usuarioService.buscarPorId(id)
                .map(u -> {
                    u.setNombreUsuario(usuario.getNombreUsuario());
                    u.setRol(usuario.getRol());
                    u.setContraseña(usuario.getContraseña());
                    return usuarioService.guardar(u);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (usuarioService.buscarPorId(id).isPresent()) {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
