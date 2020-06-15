package com.ada.cursos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.cursos.form.LoginForm;
import com.ada.cursos.model.Usuario;
import com.ada.cursos.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	public Usuario porId(Long id) {
		
		java.util.Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
		Usuario usuario = usuarioOp.get();
		
		return usuario;
	}
	
	
	public Usuario generarUsuarioDeForm (LoginForm loginForm) {
		
		Usuario usuario = new Usuario();
		usuario.setEmail(loginForm.getEmail());
		usuario.setPass(loginForm.getPass());
		
		return usuario;
	}

}
