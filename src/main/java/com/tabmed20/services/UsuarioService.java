package com.tabmed20.services;

import com.tabmed20.model.Usuario;
import com.tabmed20.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarUsuarioPorCpfESenha(String cpf, String senha) {
        return usuarioRepository.findByCpfAndSenha(cpf, senha);
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setSobrenome(usuarioAtualizado.getSobrenome());
            usuario.setCpf(usuarioAtualizado.getCpf());
            usuario.setSenha(usuarioAtualizado.getSenha());
            usuario.setTipoAcesso(usuarioAtualizado.getTipoAcesso());
            usuario.setAtivo(usuarioAtualizado.isAtivo());
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    public void ativarDesativarUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            Usuario u = usuario.get();
            u.setAtivo(!u.isAtivo());
            usuarioRepository.save(u);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    public Optional<Usuario> validarUsuarioAtivo(String cpf, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByCpfAndSenha(cpf, senha);
        if (usuario.isPresent() && usuario.get().isAtivo()) {
            return usuario;
        }
        return Optional.empty();
    }
}