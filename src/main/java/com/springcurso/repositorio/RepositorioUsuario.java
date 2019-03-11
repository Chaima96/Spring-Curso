package com.springcurso.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springcurso.domain.Usuario;
import com.springcurso.domain.enums.Role;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
	
	
	/*OPTINAL E UM TIPO DE DADO QUE NOS PERMITE TRATAR DA 
	 * MELHOR MANEIRA VALORES NULOS
	 */
	@Query("SELECT u FROM usuario u WHERE email = ?1 AND password = ?2")
	public Optional<Usuario> login(String email, String password);
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE usuario SET role = ?2 WHERE id = ?1")
	public int UpdateRole(Long id, Role role);

}
