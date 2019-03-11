package com.springcurso.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springcurso.domain.Estado_Pedido;

@Repository
public interface EstadosPedidoRepositorio extends JpaRepository<Estado_Pedido, Long> {

	public List<Estado_Pedido> findAllByPedidoId(Long id);
	public Page<Estado_Pedido> findAllByPedidoId(Long id, Pageable pageable);
}
