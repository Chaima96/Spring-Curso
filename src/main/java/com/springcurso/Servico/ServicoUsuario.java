package com.springcurso.Servico;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springcurso.domain.Usuario;
import com.springcurso.exception.NotFoundException;
import com.springcurso.model.PageModel;
import com.springcurso.model.PageRequestModel;
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
		
		// ()-> new NotFoundException() Funcao row funtion
		return result.orElseThrow(()-> new NotFoundException("Nao existe usuario com esse ID " + id));
		
	}


	public List<Usuario> listaAll(){
		
		List<Usuario> lista = repositoriousuario.findAll();
		return  lista;
	}
	
	public PageModel<Usuario> listAllOnLazzMode(PageRequestModel pr){
		
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		
		Page<Usuario> page = repositoriousuario.findAll(pageable);
		
		PageModel<Usuario> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		
		return pm;
		}

	public Usuario login(String email, String password) {
		
		password = HashUtil.getSecureHash(password);
		
		Optional<Usuario> usuario = repositoriousuario.login(email, password);
		return usuario.get();
	}
	
	public int UpdateRole(Usuario usuario) {
		
		return repositoriousuario.UpdateRole(usuario.getId(), usuario.getRole());
	}


}
