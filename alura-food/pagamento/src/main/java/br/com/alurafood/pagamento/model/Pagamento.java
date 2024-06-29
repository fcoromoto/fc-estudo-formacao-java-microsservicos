package br.com.alurafood.pagamento.model;

import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pagamentos")
public class Pagamento {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Positive
  @NotNull
  @Column(name = "valor", precision = 19, scale = 2)
  private BigDecimal valor;

  @NotBlank
  @Size(max = 100)
  @Column(name = "nome", length = 100)
  private String nome;

  @Size(max = 19)
  @Column(name = "numero", length = 19)
  private String numero;

  @Size(max = 7)
  @Column(name = "expiracao", length = 7)
  private String expiracao;

  @Size(max = 3, min = 3)
  @Column(name = "cvc", length = 3)
  private String cvc;

  @Enumerated(EnumType.STRING)
  @NotNull
  @Column(name = "status")
  private Status status;

  @NotNull
  @Column(name = "forma_pagamento_id")
  private Long formaPagamentoId;

  @NotNull
  @Column(name = "pedido_id")
  private Long pedidoId;

}
