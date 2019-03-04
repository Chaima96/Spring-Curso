package com.springcurso.recurso;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcurso.domain.Estado_Pedido;

import Servicos.ServicoEstadoPedido;

@RestController
@RequestMapping(value = "estado-pedido")
public class RecursoEstadoPedido {
	
	
	@Autowired private ServicoEstadoPedido servicoEstadoPedido;
	
	@PostMapping 
	public ResponseEntity<Estado_Pedido> Salvar(@RequestBody Estado_Pedido estado_pedido){
		
		Estado_Pedido estadoCriado = servicoEstadoPedido.salvar(estado_pedido);
		return ResponseEntity.status(HttpStatus.CREATED).body(estadoCriado);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estado_Pedido> getById(@PathVariable(name =  "id") Long id){
		
		Estado_Pedido estado = servicoEstadoPedido.getById(id);
		return ResponseEntity.ok(estado);
	}
	
	
	

}
