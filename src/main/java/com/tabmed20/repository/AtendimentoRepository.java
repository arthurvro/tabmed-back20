package com.tabmed20.repository;

import com.tabmed20.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    List<Atendimento> findByMedicoId(Long medicoId);
    List<Atendimento> findByPacienteId(Long pacienteId);
}
