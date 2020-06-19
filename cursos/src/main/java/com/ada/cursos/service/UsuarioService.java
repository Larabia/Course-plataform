package com.ada.cursos.service;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.cursos.form.LoginForm;
import com.ada.cursos.model.Usuario;
import com.ada.cursos.repository.CursoRepository;
import com.ada.cursos.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	Logger log = Logger.getLogger(CursoRepository.class.getName());
	
	public Usuario porId(Long id) {
		
		Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
		
		if (Optional.empty().equals(usuarioOp)) {
			log.info("El id ingresado no existe.");
		}
		Usuario usuario = usuarioOp.get();
		
		return usuario;
	}
	
	
	public Usuario cargarDatosForm (LoginForm loginForm, Usuario usuario) {
		
		usuario.setEmail(loginForm.getEmail());
		usuario.setPass(loginForm.getPass());
		
		return usuario;
	}

}
