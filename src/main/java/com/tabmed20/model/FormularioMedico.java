package com.tabmed20.model;

import jakarta.persistence.*;

@Entity
public class FormularioMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dados;
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Usuario medico;

    // Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDados() {
        return dados;
    }
    public void setDados(String dados) {
        this.dados = dados;
    }
    public Usuario getMedico() {
        return medico;
    }
    public void setMedico(Usuario medico) {
        this.medico = medico;
    }
}
