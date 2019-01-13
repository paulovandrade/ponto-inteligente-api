package com.pauloandrade.pontointeligente.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empresa")
public class Empresa implements Serializable {
    private static final long serialVersionUID = 2506062531112592069L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDate dataAtualizacao;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;

    @PreUpdate
    private void preUpdate(){
        this.dataAtualizacao = LocalDate.now();
    }

    @PrePersist
    private void prePersist(){
        this.dataCriacao = LocalDate.now();
        this.dataAtualizacao = LocalDate.now();
    }
}
