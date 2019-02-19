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

import com.springcurso.domain.Estado_Pedido;
import com.springcurso.domain.Pedido;
import com.springcurso.domain.Usuario;
import com.springcurso.domain.enums.EstadosdoPedido;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RepositorioEstadoPedidoTeste {
	
	@Autowired private EstadosPedidoRepositorio estadospedidoRepositorio;
	
	
	@Test
	public void AsalvarTeste() {
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		
		Pedido pedido = new Pedido();
		pedido.setId(1L);
		
		
		Estado_Pedido estado  = new Estado_Pedido(null, "Foi Comprado um novo laptop de marca HP de 16 GB RAM", new Date(), EstadosdoPedido.FECHADO, pedido, usuario);
	
		Estado_Pedido estadocriado = estadospedidoRepositorio.save(estado);
		
		assertThat(estadocriado.getId()).isEqualTo(1L);
		
		
	}
	
	
	
	@Test
	public void getByEstado_PedidoId() {
		
		Optional<Estado_Pedido> resultado = estadospedidoRepositorio.findById(1L);
		Estado_Pedido estado = resultado.get();
		
		assertThat(estado.getDescricao()).isEqualTo("Foi Comprado um novo laptop de marca HP de 16 GB RAM");
		
	}
	
	@Test
	public void listarByEstado_PedidoId() {
		
		List<Estado_Pedido> estado = estadospedidoRepositorio.findAllByPedidoId(1L);
		assertThat(estado.size()).isEqualTo(1);
		
	}


}
