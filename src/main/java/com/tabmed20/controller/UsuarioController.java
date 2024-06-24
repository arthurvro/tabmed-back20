package com.tabmed20.controller;

import com.tabmed20.model.Usuario;
import com.tabmed20.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioAtivo = usuarioService.validarUsuarioAtivo(usuario.getCpf(), usuario.getSenha());
        if (usuarioAtivo.isPresent()) {
            return ResponseEntity.ok(usuarioAtivo.get());
        } else {
            return ResponseEntity.status(401).body("Usuário não encontrado ou desativado.");
        }
    }

    @GetMapping
    public ResponseEntity<?> listarUsuarios(@RequestHeader("tipoAcesso") Integer tipoAcesso) {
        if (tipoAcesso != null && (tipoAcesso == 1 || tipoAcesso == 2)) { // Médicos e recepcionistas podem listar usuários
            return ResponseEntity.ok(usuarioService.listarUsuarios());
        } else {
            return ResponseEntity.status(403).body("Acesso negado");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long id, @RequestHeader("tipoAcesso") Integer tipoAcesso) {
        if (tipoAcesso != null && tipoAcesso == 2) {
            return usuarioService.buscarUsuarioPorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.status(403).body("Acesso negado");
        }
    }

    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario, @RequestHeader("tipoAcesso") Integer tipoAcesso) {
        if (tipoAcesso != null && tipoAcesso == 2) {
            Usuario novoUsuario = usuarioService.salvarUsuario(usuario);
            return ResponseEntity.ok(novoUsuario);
        } else {
            return ResponseEntity.status(403).body("Acesso negado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado, @RequestHeader("tipoAcesso") Integer tipoAcesso) {
        if (tipoAcesso != null && tipoAcesso == 2) {
            try {
                return ResponseEntity.ok(usuarioService.atualizarUsuario(id, usuarioAtualizado));
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(403).body("Acesso negado");
        }
    }

    @PutMapping("/ativar-desativar/{id}")
    public ResponseEntity<?> ativarDesativarUsuario(@PathVariable Long id, @RequestHeader("tipoAcesso") Integer tipoAcesso) {
        if (tipoAcesso != null && tipoAcesso == 2) {
            try {
                usuarioService.ativarDesativarUsuario(id);
                return ResponseEntity.ok().build();
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(403).body("Acesso negado");
        }
    }
}
