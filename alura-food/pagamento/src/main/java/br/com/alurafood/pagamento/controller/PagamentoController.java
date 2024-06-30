package br.com.alurafood.pagamento.controller;

import br.com.alurafood.pagamento.dto.PagamentoDto;
import br.com.alurafood.pagamento.service.PagamentoService;
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
@RequestMapping("/pagamentos")
public class PagamentoController {

  private final PagamentoService service;

  @GetMapping
  public ResponseEntity<Page<PagamentoDto>> obterTodosOsPagamentos(@PageableDefault(size = 10) Pageable pageable) {
    return ResponseEntity.ok(service.obterTodosOsPagamentos(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PagamentoDto> obterPorId(@PathVariable Long id) {
    return ResponseEntity.ok(service.obterPorId(id));
  }

  @PostMapping
  public ResponseEntity<PagamentoDto> criarPagamento(@RequestBody PagamentoDto pagamentoDto, UriComponentsBuilder uriBuilder) {
    var pagamento = service.criarPagamento(pagamentoDto);
    var endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();
    return ResponseEntity.created(endereco).body(pagamento);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PagamentoDto> atualizarPagamento(@PathVariable Long id, @RequestBody PagamentoDto pagamentoDto) {
    return ResponseEntity.ok(service.atualizarPagamento(id, pagamentoDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarPagamento(@PathVariable Long id) {
    service.deletarPagamento(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}/confirmar")
  public void confirmarPagamento(@PathVariable @NotNull Long id){
    service.confirmarPagamento(id);
  }
}
