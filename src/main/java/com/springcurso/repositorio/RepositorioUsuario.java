package com.springcurso.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springcurso.domain.Usuario;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
	
	
	/*OPTINAL E UM TIPO DE DADO QUE NOS PERMITE TRATAR DA 
	 * MELHOR MANEIRA VALORES NULOS
	 */
	@Query("SELECT FROM Usuario WHERE email = ?1 AND password = ?2")
	public Optional<Usuario> login(String email, String password);

}
