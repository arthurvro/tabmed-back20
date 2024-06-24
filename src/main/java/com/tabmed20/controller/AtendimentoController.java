package com.tabmed20.controller;

import com.tabmed20.model.Atendimento;
import com.tabmed20.model.Usuario;
import com.tabmed20.services.AtendimentoService;
import com.tabmed20.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/atendimentos")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Atendimento> criarAtendimento(@RequestBody Atendimento atendimento) {
        Optional<Usuario> medico = usuarioService.buscarUsuarioPorId(atendimento.getMedico().getId());
        Optional<Usuario> paciente = usuarioService.buscarUsuarioPorId(atendimento.getPaciente().getId());

        if (medico.isPresent() && paciente.isPresent()) {
            atendimento.setMedico(medico.get());
            atendimento.setPaciente(paciente.get());
            Atendimento novoAtendimento = atendimentoService.salvarAtendimento(atendimento);
            return ResponseEntity.ok(novoAtendimento);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Atendimento>> listarAtendimentosPorMedico(@PathVariable Long medicoId) {
        return ResponseEntity.ok(atendimentoService.listarAtendimentosPorMedico(medicoId));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Atendimento>> listarAtendimentosPorPaciente(@PathVariable Long pacienteId) {
        List<Atendimento> atendimentos = atendimentoService.listarAtendimentosPorPaciente(pacienteId);
        for (Atendimento atendimento : atendimentos) {
            atendimento.getMedico().setSenha(null); // Para não enviar a senha do médico
        }
        return ResponseEntity.ok(atendimentos);
    }
}
