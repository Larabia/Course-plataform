package com.ada.cursos.controller;


import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ada.cursos.form.UsuarioForm;
import com.ada.cursos.model.Usuario;
import com.ada.cursos.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/usuarioController")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	Logger log = Logger.getLogger(UsuarioRepository.class.getName());

	// POST
	@PostMapping(path = "/alta")
	@Operation (summary = "Alta usuario", description = "Ingrasa un objeto usuario a la base de datos")
	
	public ResponseEntity<Usuario> altaUsuairo(@RequestBody UsuarioForm usuarioForm) {
				
		log.info("metodo: altaUsuario.");
		
		Usuario usuario = new Usuario();
		usuario.setEmail(usuarioForm.getEmail());
		usuario.setPass(usuarioForm.getPass());
		
		usuarioRepo.save(usuario);
		log.info("metodo: Usuario guardado.");

		return new ResponseEntity<>(usuario, HttpStatus.CREATED);

	}
	
	
}

