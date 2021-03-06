package com.ada.cursos.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ada.cursos.exceptions.IdInexistenteException;
import com.ada.cursos.form.UsuarioForm;
import com.ada.cursos.model.Usuario;
import com.ada.cursos.repository.UsuarioRepository;
import com.ada.cursos.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioServ;

	Logger log = Logger.getLogger(UsuarioRepository.class.getName());

	@GetMapping(path = "/{id}")
	@Operation(summary = "usuarioPorId", description = "Recibe como un Long id y devuelve el usuario con el id correspondiente.")
	public ResponseEntity<Usuario> usuarioPorId(@PathVariable Long id) {

		log.info("Metodo usuarioPorId: buscando usuario id" + id);
		Usuario usuario;
		try {
			usuario = usuarioServ.porId(id);

			log.info("Usuario encontrado");
			return new ResponseEntity<>(usuario, HttpStatus.OK);

		} catch (IdInexistenteException e) {

			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(path = "/")
	@Operation(summary = "listarUsuarios", description = "Lista todos los usuarios presentes en la base de datos.")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Usuario>> listarUsuarios() {

		log.info("Metodo listarUsuarios: listando usuario...");
		List<Usuario> listarUsuarios = usuarioServ.listar();

		log.info("Listado completo: listarUsuarios.");
		return new ResponseEntity<>(listarUsuarios, HttpStatus.OK);
	}

	@PutMapping(path = "/modificar")
	@Operation(summary = "modificarUsuario", description = "Recibe un Long id y un loginForm, busca el usuario por id y lo actualiza con los datos del formulario.")
	public ResponseEntity<Usuario> modificarUsuario(@RequestBody UsuarioForm usuarioForm) {

		log.info("Metodo modificarUsuario: buscando usuario...");
		Usuario usuario;
		try {
			usuario = usuarioServ.porId(usuarioForm.getId());

			log.info("Modificando usuario...");
			usuario = usuarioServ.cargarDatosForm(usuarioForm, usuario);
			usuarioServ.guardar(usuario);

			log.info("Usuario modificado.");
			return new ResponseEntity<>(usuario, HttpStatus.OK);

		} catch (IdInexistenteException e) {

			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping(path = "/{id}")
	@Operation(summary = "borrarUsuario", description = "Recibe un Long id, busca el usuario por id y lo borra de la base de datos.")
	public ResponseEntity<Object> borrarUsuario(@PathVariable Long id) {

		log.info("Metodo borrarUsuario: buscando usuario...");
		Usuario usuario;
		try {
			usuario = usuarioServ.porId(id);

			log.info("Borrando usuario id  " + id);
			usuarioServ.borrar(usuario);

			log.info("Usuario borrado.");
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (IdInexistenteException e) {

			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
