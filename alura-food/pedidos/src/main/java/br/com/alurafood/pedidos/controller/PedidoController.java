package br.com.alurafood.pedidos.controller;

import br.com.alurafood.pedidos.dto.PedidoDto;
import br.com.alurafood.pedidos.service.PedidoService;
import java.net.URI;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

  private final PedidoService pedidoService;

  @PostMapping
  public ResponseEntity<PedidoDto> criarPedido(@RequestBody PedidoDto pedidoDto,  UriComponentsBuilder uriBuilder) {
    PedidoDto pedido = pedidoService.criarPedido(pedidoDto);
    URI endereco = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
    return ResponseEntity.created(endereco).body(pedido);
  }

  
}
