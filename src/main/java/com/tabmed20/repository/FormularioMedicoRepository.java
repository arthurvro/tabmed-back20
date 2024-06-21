package com.tabmed20.repository;

import com.tabmed20.model.FormularioMedico;
import com.tabmed20.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormularioMedicoRepository extends JpaRepository<FormularioMedico, Long> {
    List<FormularioMedico> findByMedico(Usuario medico);
}
