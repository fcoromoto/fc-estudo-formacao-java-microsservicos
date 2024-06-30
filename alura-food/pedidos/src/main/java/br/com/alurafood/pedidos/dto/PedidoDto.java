package br.com.alurafood.pedidos.dto;

import br.com.alurafood.pedidos.model.StatusPedido;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDto {


  private Long id;
  private LocalDateTime dataHora;
  private StatusPedido status;
  private List<ItemPedidoDto> itemPedidos = new ArrayList<>();

}
