package com.ada.cursos.service;

import org.springframework.stereotype.Service;

import com.ada.cursos.form.LoginForm;
import com.ada.cursos.model.Usuario;

@Service
public class UsuarioService {
	
	public Usuario generarUsuarioDeForm (LoginForm loginForm) {
		
		Usuario usuario = new Usuario();
		usuario.setEmail(loginForm.getEmail());
		usuario.setPass(loginForm.getPass());
		
		return usuario;
	}

}
