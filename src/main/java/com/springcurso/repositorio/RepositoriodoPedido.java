package com.springcurso.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springcurso.domain.Estado_Pedido;
import com.springcurso.domain.Pedido;

@Repository
public interface RepositoriodoPedido extends JpaRepository<Pedido, Long> {
	
	public List<Pedido> findAllByUsuarioId(Long id);
	
	@Query("UPDATE pedido SET estado = ?2 WHERE id = ?1")
	public Pedido ActualizarEstado(Long id, Estado_Pedido estado);


}
