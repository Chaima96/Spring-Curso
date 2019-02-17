package com.springcurso.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springcurso.domain.Estado_Pedido;

@Repository
public interface EstadosPedidoRepositorio extends JpaRepository<Estado_Pedido, Long> {

	public List<Estado_Pedido> findAllPedidoId(Long id);
}
