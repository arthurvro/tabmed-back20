package com.tabmed20.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Atendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Usuario medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Usuario paciente;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String historicoPaciente;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String queixasPaciente;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String laudoMedico;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String receitaMedica;
}


