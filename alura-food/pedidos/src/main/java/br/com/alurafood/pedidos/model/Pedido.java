package br.com.alurafood.pedidos.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pedidos")
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotNull
  @Column(name = "data_hora")
  private LocalDateTime dataHora;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private StatusPedido status;

  @OneToMany(mappedBy = "pedido", cascade=CascadeType.PERSIST)
  private List<ItemPedido> itemPedidos = new ArrayList<>();

}
