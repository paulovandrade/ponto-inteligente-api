package com.pauloandrade.pontointeligente.api.entities;

import com.pauloandrade.pontointeligente.api.enums.TipoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lancamento")
public class Lancamento implements Serializable {
    private static final long serialVersionUID = 7280347176690955041L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "localizacao")
    private String localizacao;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDate dataAtualizacao;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoEnum tipo;

    @ManyToOne
    private Funcionario funcionario;

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
