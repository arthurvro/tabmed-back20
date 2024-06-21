package com.tabmed20.services;

import com.tabmed20.model.FormularioMedico;
import com.tabmed20.model.Usuario;
import com.tabmed20.repository.FormularioMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormularioMedicoService {
    @Autowired
    private FormularioMedicoRepository formularioMedicoRepository;

    public FormularioMedico salvarFormularioMedico(FormularioMedico formularioMedico) {
        return formularioMedicoRepository.save(formularioMedico);
    }

    public List<FormularioMedico> buscarFormulariosPorMedico(Usuario medico) {
        return formularioMedicoRepository.findByMedico(medico);
    }
}
