package com.tabmed20.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sobrenome;
    private String cpf;
    private String senha;
    private int tipoAcesso; // 1 para Médico, 2 para Recepção, 3 para Paciente
    private boolean ativo; // Campo para indicar se o usuário está ativo ou não
}
