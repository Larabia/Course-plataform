package com.ada.cursos.service;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ada.cursos.form.LoginForm;
import com.ada.cursos.model.Usuario;
import com.ada.cursos.repository.CursoRepository;
import com.ada.cursos.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    
    Logger log = Logger.getLogger(CursoRepository.class.getName());

    public Optional<Usuario> getByNombreUsuario(String nu){
        return usuarioRepository.findByUserName(nu);
    }

    public boolean existePorNombre(String nu){
        return usuarioRepository.existsByUserName(nu);
    }

    public  boolean existePorEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void guardar(Usuario usuario){
        usuarioRepository.save(usuario);
    }

	public Usuario porId(Long id) {

		Optional<Usuario> usuarioOp = usuarioRepository.findById(id);

		if (Optional.empty().equals(usuarioOp)) {
			log.info("El id ingresado no existe.");
		}
		Usuario usuario = usuarioOp.get();

		return usuario;
	}

	public Usuario cargarDatosForm(LoginForm loginForm, Usuario usuario) {

		usuario.setUserName(loginForm.getUserName());
		usuario.setEmail(loginForm.getEmail());
		usuario.setPassword(loginForm.getPass());

		return usuario;
	}

}
