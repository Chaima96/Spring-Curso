 package com.springcurso.Servico;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springcurso.domain.Estado_Pedido;
import com.springcurso.domain.enums.EstadosdoPedido;
import com.springcurso.model.PageModel;
import com.springcurso.model.PageRequestModel;
import com.springcurso.repositorio.EstadosPedidoRepositorio;
import com.springcurso.repositorio.RepositoriodoPedido;

@Service
public class ServicoEstadoPedido {
	
	@Autowired private EstadosPedidoRepositorio estadosPedidoRepositorio;
	@Autowired private RepositoriodoPedido repodopedido;
	
	public Estado_Pedido salvar(Estado_Pedido estadopedido) {
		
		 estadopedido.setDatadeRealizacao(new Date());
		 Estado_Pedido criadoEstado = estadosPedidoRepositorio.save(estadopedido);
		 
		 Long pedidoId = estadopedido.getPedido().getId();
		 EstadosdoPedido estado = estadopedido.getEstado_pedido();
		 
		 repodopedido.ActualizarEstado(pedidoId, estado);
		 
		 return criadoEstado;
	}
	
	public Estado_Pedido getById(Long id) {
		
		Optional<Estado_Pedido> result = estadosPedidoRepositorio.findById(id);
		return result.get();
	}
	
	public List<Estado_Pedido> listarTodosByPedidoId(Long pedido_id){
		
		List<Estado_Pedido> estado = estadosPedidoRepositorio.findAllByPedidoId(pedido_id);
		
		return estado;
	}
	
     public PageModel<Estado_Pedido> listAllByEstado_PedidoIdOnLazzModel(Long id, PageRequestModel pr){
		
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
	    Page<Estado_Pedido>  page = estadosPedidoRepositorio.findAllByPedidoId(id, pageable);
	    PageModel<Estado_Pedido> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		
	    return pm;
	}

}
