package com.springcurso.recurso;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcurso.Servico.ServicoEstadoPedido;
import com.springcurso.Servico.ServicoPedido;
import com.springcurso.domain.Estado_Pedido;
import com.springcurso.domain.Pedido;
import com.springcurso.model.PageModel;
import com.springcurso.model.PageRequestModel;



@RestController
@RequestMapping(value = "pedidos")
public class RecusodoPediso {
	
	@Autowired private ServicoPedido servicoPedido;
	@Autowired private ServicoEstadoPedido servicoEstadoPedido;
	
	
	@PostMapping
	public ResponseEntity<Pedido> salvar(@RequestBody Pedido pedido){
		
		Pedido pedidoCriado = servicoPedido.salvar(pedido);
		return ResponseEntity.ok(pedidoCriado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pedido> Actualiza(@PathVariable(name = "id") Long id, @RequestBody Pedido pedido){
		
		pedido.setId(id);
		Pedido pedidoActualizado = servicoPedido.actualizar(pedido);
		return ResponseEntity.ok(pedidoActualizado);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> getById(@PathVariable(name = "id") Long id){
		
		Pedido pedido = servicoPedido.getById(id);
		return ResponseEntity.ok(pedido);
		
	}
	
	/*
	@GetMapping
	public ResponseEntity<List<Pedido>> listAll(){
		
		List<Pedido> pedidos = servicoPedido.listarTodos();
		return ResponseEntity.ok(pedidos);
	}
	*/
	
	@GetMapping
	public ResponseEntity<PageModel<Pedido>> listAll(
			@RequestParam(value = "pagina", defaultValue = "0") int pagina,
			@RequestParam(value = "tamanho", defaultValue = "10") int tamanho){
		
		PageRequestModel pr = new PageRequestModel(pagina, tamanho);
		PageModel<Pedido> pm = servicoPedido.listAllOnLazzMode(pr);
		return ResponseEntity.ok(pm);
	}
	
	/*
	@GetMapping("/{id}/estado")
	public ResponseEntity<List<Estado_Pedido>> listAllEstadosById(@PathVariable(name = "id") Long id){
		
		List<Estado_Pedido> estados = servicoEstadoPedido.listarTodosByPedidoId(id);
		return ResponseEntity.ok(estados);
		
	}
	*/
	
	@GetMapping("/{id}/estado")
	public ResponseEntity<PageModel<Estado_Pedido>> listAllEstadosById(
			@PathVariable(name = "id") Long id,
			@RequestParam(value = "pagina", defaultValue = "0") int pagina, 
			@RequestParam(value = "tamanho", defaultValue = "10") int tamanho){
		
		PageRequestModel pr = new PageRequestModel(pagina, tamanho);
		PageModel<Estado_Pedido> pm = servicoEstadoPedido.listAllByEstado_PedidoIdOnLazzModel(id, pr);
		return ResponseEntity.ok(pm);
		
	}
	
	
}
