package com.springcurso.repositorio;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.springcurso.domain.Pedido;
import com.springcurso.domain.enums.EstadosdoPedido;

@Repository
public interface RepositoriodoPedido extends JpaRepository<Pedido, Long> {
	
	public List<Pedido> findAllByUsuarioId(Long id);
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE pedido SET estado = ?2 WHERE id = ?1")
	public int ActualizarEstado(Long id, EstadosdoPedido estado);


}
