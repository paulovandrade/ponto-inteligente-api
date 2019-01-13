package com.pauloandrade.pontointeligente.api.entities;

import com.pauloandrade.pontointeligente.api.enums.PerfilEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "funcionario")
public class Funcionario implements Serializable {
    private static final long serialVersionUID = -3979662611446054182L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "valor_hora")
    private BigDecimal valorHora;

    @Column(name = "qtd_horas_trabalho_dia")
    private Float qtdHorasTrabalhoDia;

    @Column(name = "qtd_horas_almoco")
    private Float qtdHorasAlmoco;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private PerfilEnum perfil;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDate dataAtualizacao;

    @ManyToOne
    private Empresa empresa;

    @OneToMany(mappedBy = "funcinario", cascade = CascadeType.ALL)
    private List<Lancamento> lancamentos;

    @PreUpdate
    private void preUpdate(){
        this.dataAtualizacao = LocalDate.now();
    }

    @PrePersist
    private void prePersist(){
        this.dataCriacao = LocalDate.now();
        this.dataAtualizacao = LocalDate.now();
    }

    @Transient
    public Optional<BigDecimal> getValorHoraOptional(){
        return Optional.ofNullable(valorHora);
    }

    @Transient
    public Optional<Float> getQtdHorasTrabalhoDiaOptional(){
        return Optional.ofNullable(qtdHorasTrabalhoDia);
    }

    @Transient
    public Optional<Float> getQtdHorasAlmocoOptional(){
        return Optional.ofNullable(qtdHorasAlmoco);
    }
}
