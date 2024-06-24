package com.tabmed20.controller;

import com.tabmed20.model.Usuario;
import com.tabmed20.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
        return usuarioService.buscarUsuarioPorCpfESenha(usuario.getCpf(), usuario.getSenha())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.buscarUsuarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        try {
            return ResponseEntity.ok(usuarioService.atualizarUsuario(id, usuarioAtualizado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/ativar-desativar/{id}")
    public ResponseEntity<Void> ativarDesativarUsuario(@PathVariable Long id) {
        try {
            usuarioService.ativarDesativarUsuario(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

//package com.tabmed20.controller;
//
//import com.tabmed20.model.Usuario;
//import com.tabmed20.services.UsuarioService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/usuarios")
//public class UsuarioController {
//    @Autowired
//    private UsuarioService usuarioService;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
//        Optional<Usuario> usuarioLogado = usuarioService.buscarUsuarioPorCpfESenha(usuario.getCpf(), usuario.getSenha());
//        if (usuarioLogado.isPresent()) {
//            return ResponseEntity.ok(usuarioLogado.get());
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
//
//    @PostMapping("/cadastro")
//    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
//        Usuario novoUsuario = usuarioService.salvarUsuario(usuario);
//        return ResponseEntity.ok(novoUsuario);
//    }
//}