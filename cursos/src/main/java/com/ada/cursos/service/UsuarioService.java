package com.ada.cursos.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.cursos.exceptions.IdInexistenteException;
import com.ada.cursos.form.UsuarioForm;
import com.ada.cursos.model.Usuario;
import com.ada.cursos.repository.CursoRepository;
import com.ada.cursos.repository.UsuarioRepository;
import com.google.common.collect.Lists;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;

	Logger log = Logger.getLogger(CursoRepository.class.getName());

	public Optional<Usuario> getByNombreUsuario(String nu) {
		return usuarioRepo.findByNombreUsuario(nu);
	}

	public boolean existePorNombre(String nu) {
		return usuarioRepo.existsByNombreUsuario(nu);
	}

	public boolean existePorEmail(String email) {
		return usuarioRepo.existsByEmail(email);
	}
	
	public boolean existePorId(Long id) {
		return usuarioRepo.existsById(id);
	}

	public Usuario guardar(Usuario usuario) {
		usuarioRepo.save(usuario);
		
		return usuario;
	}

	public Usuario porId(Long id) throws IdInexistenteException {

		Optional<Usuario> usuarioOp = usuarioRepo.findById(id);

		if (Optional.empty().equals(usuarioOp)) {
			throw new IdInexistenteException ("El id ingresado no existe.");
		}
		Usuario usuario = usuarioOp.get();

		return usuario;
	}
     
	public void borrar(Usuario usuario) {
		
		usuarioRepo.delete(usuario);
		log.info("Usuario borrado.");
	}
     
	
	public List<Usuario> listar() {
		
		Iterable<Usuario> ListUsuarioIt = usuarioRepo.findAll();
		List<Usuario> listadoUsuarios = Lists.newArrayList(ListUsuarioIt);
		
		return listadoUsuarios;
	}

	public Usuario cargarDatosForm(UsuarioForm usuarioForm, Usuario usuario) {

		usuario.setNombreUsuario(usuarioForm.getNombreUsuario());
		usuario.setEmail(usuarioForm.getEmail());
		usuario.setPassword(usuarioForm.getPassword());
		;

		return usuario;
	}

}
