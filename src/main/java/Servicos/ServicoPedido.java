package Servicos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcurso.domain.Pedido;
import com.springcurso.domain.enums.EstadosdoPedido;
import com.springcurso.repositorio.RepositoriodoPedido;

@Service
public class ServicoPedido {
	
	@Autowired private RepositoriodoPedido repositoriodopedido;
	
	public Pedido salvar(Pedido pedido) {
		
		 pedido.setEstado(EstadosdoPedido.ABERTO);
		 pedido.setCriacaoData(new Date());
		 
		 Pedido pedidocriado = repositoriodopedido.save(pedido);
		 return pedidocriado;
		
	}
	
	
	public Pedido actualizar(Pedido pedido) {
		
		Pedido pedidoactualizado  = repositoriodopedido.save(pedido);
		return pedidoactualizado;
		
	}
	
	public Pedido getById(Long id) {
		
		Optional<Pedido> result = repositoriodopedido.findById(id);
		return result.get();
	}
	
	public List<Pedido> listarTodos(){
		
		List<Pedido> lista = repositoriodopedido.findAll();
		return lista;
	}
	
	public List<Pedido> listarTodosByUsuarioId(Long id_usuario) {
		
		List<Pedido> pedido = repositoriodopedido.findAllByUsuarioId(id_usuario);
		return pedido;
	}
	

}
