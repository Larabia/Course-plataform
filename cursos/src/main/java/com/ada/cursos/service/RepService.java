package com.ada.cursos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.cursos.form.RepForm;
import com.ada.cursos.model.Rep;
import com.ada.cursos.model.Usuario;
import com.ada.cursos.repository.UsuarioRepository;


@Service
public class RepService {
	
	@Autowired
	private UsuarioRepository usuarioRepo;

	public Rep generarRepDeForm(RepForm repForm) {

		Rep rep = new Rep();

		java.util.Optional<Usuario> usuarioOp = usuarioRepo.findById(repForm.getId());
		Usuario usuario = usuarioOp.get();

		rep.setUsuario(usuario);
		rep.setNombre(repForm.getNombre());
		rep.setApellido(repForm.getApellido());
		
		return rep;

	}

}
