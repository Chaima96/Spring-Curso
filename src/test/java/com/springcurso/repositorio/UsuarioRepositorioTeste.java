package com.springcurso.repositorio;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springcurso.domain.Usuario;
import com.springcurso.domain.enums.Role;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class UsuarioRepositorioTeste {
	
	@Autowired private RepositorioUsuario usuariorepositorio; 
	
	@Ignore
	//@Test
	public void AsalvarTeste() {
		
		Usuario usuario = new Usuario(null, "Lucas", "lucaschaima@gmail.com", "123", Role.ADMINISTRADOR, null, null);
		Usuario createdUser = usuariorepositorio.save(usuario);
		
		assertThat(createdUser.getId()).isEqualTo(1L);
		
	}
	
	@Ignore
	//@Test
	public void AtualizarTeste() {
		
		Usuario usuario = new Usuario(1L, "Lucas Chaima", "lucaschaima@gmail.com", "123", Role.ADMINISTRADOR, null, null);
		Usuario updatreddUser = usuariorepositorio.save(usuario);
		
		assertThat(updatreddUser.getNome()).isEqualTo("Lucas Chaima");
	}
	
	@Ignore
	//@Test
	public void getByIdTeste() {
		
		Optional<Usuario> resultado = usuariorepositorio.findById(1L);
		Usuario usuario = resultado.get();
		
		assertThat(usuario.getPassword()).isEqualTo("123");
	}
	
	@Ignore
	//@Test
	public void listarTeste() {
		
		List<Usuario> usuarios = usuariorepositorio.findAll();
		
		assertThat(usuarios.size()).isEqualTo(1);
		
	}
	
	@Ignore
	//@Test
	public void loginTeste() {
		
		Optional<Usuario> result = usuariorepositorio.login("lucaschaima@gmail.com", "123");
		Usuario usuarioLogado = result.get();
		
		assertThat(usuarioLogado.getId()).isEqualTo(1L);
		
	}
	
	@Test
	public void UpdateRoleTest() {
		
		int affectadrows = usuariorepositorio.UpdateRole(3L, Role.ADMINISTRADOR);
		assertThat(affectadrows).isEqualTo(1);
	}

}
