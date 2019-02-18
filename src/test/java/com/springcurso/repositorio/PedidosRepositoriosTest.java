package com.springcurso.repositorio;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springcurso.domain.Pedido;
import com.springcurso.domain.Usuario;
import com.springcurso.domain.enums.EstadosdoPedido;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class PedidosRepositoriosTest {
	
	@Autowired private  RepositoriodoPedido  repositoriodopedido;
	
	@Test
	public void ASalvarTeste() {
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		Pedido pedido = new Pedido(null, "Novo laptop Hp", "Pretendo Obter um laptop hp", new Date(), EstadosdoPedido.ABERTO, usuario, null);
		
		Pedido criadoPedido = repositoriodopedido.save(pedido);
		
		assertThat(criadoPedido.getId()).isEqualTo(1L);
	}
	
	@Test
	public void ActulizarTeste() {
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		Pedido pedido = new Pedido(1L, "Novo laptop Hp", "Pretendo Obter um laptop hp, RAM 16 GB", null, EstadosdoPedido.ABERTO, usuario, null);
		
		Pedido actualizadoPedido = repositoriodopedido.save(pedido);
		
		assertThat(actualizadoPedido.getDescricao()).isEqualTo("Pretendo Obter um laptop hp, RAM 16 GB");
	}
	
	@Test
	public void getById() {
		
		Optional<Pedido> resultado = repositoriodopedido.findById(1L);
		Pedido pedido = resultado.get();
		
		assertThat(pedido.getSubject()).isEqualTo("Novo laptop Hp");
		
	}
	
	@Test
	public void ListarTeste() {
		
		List<Pedido> pedidos = repositoriodopedido.findAll();
		assertThat(pedidos.size()).isEqualTo(1);
		
		
	}
	
	@Test
	public void ListarByUsuarioIdTeste() {
		
		List<Pedido> pedidos = repositoriodopedido.findAllByUsuarioId(1L);
		assertThat(pedidos.size()).isEqualTo(1);
		
		
	}
	
	@Test
	public void ActualizaEstadoTeste() {
		
		
		int afetalinha = repositoriodopedido.ActualizarEstado( 1L, EstadosdoPedido.EM_PROGRESSO);
		assertThat(afetalinha).isEqualTo(1);
		
		
	}

}
