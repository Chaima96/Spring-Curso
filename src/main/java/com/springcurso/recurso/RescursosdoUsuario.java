package com.springcurso.recurso;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcurso.DTO.UsuarioLoginDTO;
import com.springcurso.DTO.UsuarioUpdateRoleDTO;
import com.springcurso.Servico.ServicoPedido;
import com.springcurso.Servico.ServicoUsuario;
import com.springcurso.domain.Pedido;
import com.springcurso.domain.Usuario;
import com.springcurso.model.PageModel;
import com.springcurso.model.PageRequestModel;


@RestController
@RequestMapping(value = "usuarios")
public class RescursosdoUsuario {
	
	@Autowired private ServicoUsuario servicodousuario;
	@Autowired private ServicoPedido servicoPedido;
	
	@PostMapping
	public ResponseEntity<Usuario> Salvar(@RequestBody Usuario usuario){
		
		Usuario usuarioCriado = servicodousuario.salvar(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> Actualizar(@PathVariable(name = "id") Long id, @RequestBody Usuario usuario){
		
		usuario.setId(id);
		Usuario usuarioAtualidado = servicodousuario.actualizar(usuario);
		return ResponseEntity.ok(usuarioAtualidado);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> ObterById(@PathVariable(name = "id") Long id){
		
		Usuario usuario = servicodousuario.getById(id);
		return ResponseEntity.ok(usuario);
	}
	
	/*
	@GetMapping
	public ResponseEntity<List<Usuario>> listaAll(){
		
		List<Usuario> usuarios = servicodousuario.listaAll();
		return ResponseEntity.ok(usuarios);
	}
	*/
	@GetMapping
	public ResponseEntity<PageModel<Usuario>> listAll(
			@RequestParam(value = "pagina", defaultValue = "0")  int pagina, 
			@RequestParam(value = "tamanho", defaultValue = "10") int tamanho){
		
		PageRequestModel pr = new PageRequestModel(pagina, tamanho);
		PageModel<Usuario> pm = servicodousuario.listAllOnLazzMode(pr);
		return ResponseEntity.ok(pm);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Usuario> login(@RequestBody UsuarioLoginDTO usuario){
		
		Usuario usuarioAutenticado = servicodousuario.login(usuario.getEmail(), usuario.getPassword());
		return ResponseEntity.ok(usuarioAutenticado);
	}
	
	
	/*
	@GetMapping("/{id}/pedidos")
	public ResponseEntity<List<Pedido>> listAllPedidoById(@PathVariable(name = "id") Long id){
		
		List<Pedido> pedidos = servicoPedido.listarTodosByUsuarioId(id);
		return ResponseEntity.ok(pedidos);
		
	}*/
	
	@GetMapping("/{id}/pedidos")
	public ResponseEntity<PageModel<Pedido>> listAllPedidoById(@PathVariable(name = "id") Long id, 
			@RequestParam(value = "pagina", defaultValue = "0") int pagina, 
			@RequestParam(value = "tamanho", defaultValue = "10") int tamanho){
		
		PageRequestModel pr = new PageRequestModel(pagina, tamanho);
		PageModel<Pedido> pm = servicoPedido.listAllByUsuarioIdOnLazzModel(id, pr);
		return ResponseEntity.ok(pm);
		
	}
	
	@PatchMapping("/role/{id}")
	public ResponseEntity<?> updateRole(@PathVariable(name = "id") Long id, @RequestBody UsuarioUpdateRoleDTO usuario){
		
		Usuario user = new Usuario();
		user.setId(id);
		user.setRole(usuario.getRole());
		
		servicodousuario.UpdateRole(user);
		
		return ResponseEntity.ok().build();
		
	}
	
	

}
