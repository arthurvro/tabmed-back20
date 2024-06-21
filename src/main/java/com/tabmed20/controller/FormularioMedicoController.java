package com.tabmed20.controller;

import com.tabmed20.model.FormularioMedico;
import com.tabmed20.model.Usuario;
import com.tabmed20.services.FormularioMedicoService;
import com.tabmed20.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/formularios")
public class FormularioMedicoController {
    @Autowired
    private FormularioMedicoService formularioMedicoService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<FormularioMedico> cadastrarFormularioMedico(@RequestBody FormularioMedico formularioMedico) {
        FormularioMedico novoFormulario = formularioMedicoService.salvarFormularioMedico(formularioMedico);
        return ResponseEntity.ok(novoFormulario);
    }

    @GetMapping("/medico/{id}")
    public ResponseEntity<List<FormularioMedico>> listarFormulariosPorMedico(@PathVariable Long id) {
        Optional<Usuario> medico = usuarioService.buscarUsuarioPorId(id);
        if (medico.isPresent() && medico.get().getTipoAcesso() == 1) {
            List<FormularioMedico> formularios = formularioMedicoService.buscarFormulariosPorMedico(medico.get());
            return ResponseEntity.ok(formularios);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
