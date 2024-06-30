package br.com.alurafood.pedidos.controller;

import br.com.alurafood.pedidos.dto.PedidoDto;
import br.com.alurafood.pedidos.dto.StatusDto;
import br.com.alurafood.pedidos.service.PedidoService;
import java.net.URI;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

  private final PedidoService service;

  @PostMapping
  public ResponseEntity<PedidoDto> criarPedido(@RequestBody PedidoDto pedidoDto,  UriComponentsBuilder uriBuilder) {
    PedidoDto pedido = service.criarPedido(pedidoDto);
    URI endereco = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
    return ResponseEntity.created(endereco).body(pedido);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PedidoDto> buscarPedido(@PathVariable Long id) {
    PedidoDto pedido = service.obterPedidoPorId(id);
    return ResponseEntity.ok(pedido);
  }

  @GetMapping
  public ResponseEntity<Page<PedidoDto>> buscarPedidos(@PageableDefault(size = 10) Pageable pageable) {
    Page<PedidoDto> pedidos = service.obterTodosPedidos(pageable);
    return ResponseEntity.ok(pedidos);
  }

  @PutMapping("/{id}/status")
  public ResponseEntity<PedidoDto> atualizaStatus(@PathVariable Long id, @RequestBody StatusDto status){
    PedidoDto dto = service.atualizarStatusPedido(id, status);
    return ResponseEntity.ok(dto);
  }

  @PutMapping("/{id}/pago")
  public ResponseEntity<Void> aprovaPagamento(@PathVariable @NotNull Long id) {
    service.aprovaPagamentoPedido(id);
    return ResponseEntity.ok().build();
  }


}
