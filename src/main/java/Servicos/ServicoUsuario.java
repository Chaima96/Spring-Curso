package Servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcurso.domain.Usuario;
import com.springcurso.repositorio.RepositorioUsuario;

import Servicos.util.HashUtil;

@Service
public class ServicoUsuario {
	
	
	@Autowired private RepositorioUsuario repositoriousuario;
	
	public Usuario salvar(Usuario usuario) {
		
		String hash = HashUtil.getSecureHash(usuario.getPassword());
		usuario.setPassword(hash);
		
		Usuario usuariocriado = repositoriousuario.save(usuario);
		return usuariocriado;
		
	}
	
	public Usuario actualizar(Usuario usuario) {
		
		String hash = HashUtil.getSecureHash(usuario.getPassword());
		usuario.setPassword(hash);
		
		Usuario usuarioactualizado = repositoriousuario.save(usuario);
		return usuarioactualizado;
	}
	
	public Usuario getById(Long id) {
		
		Optional<Usuario> result = repositoriousuario.findById(id);
		 
		return result.get();
		
	}


	public List<Usuario> listaAll(){
		
		List<Usuario> lista = repositoriousuario.findAll();
		return  lista;
	}

	public Usuario login(String email, String password) {
		
		password = HashUtil.getSecureHash(password);
		
		Optional<Usuario> usuario = repositoriousuario.login(email, password);
		return usuario.get();
	}


}
