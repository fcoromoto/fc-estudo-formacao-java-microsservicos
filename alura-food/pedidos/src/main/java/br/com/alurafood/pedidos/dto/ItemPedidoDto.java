package br.com.alurafood.pedidos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDto {

  private Long id;
  private String descricao;
  private Integer quantidade;

}
