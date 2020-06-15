package com.ada.cursos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.cursos.form.RepForm;
import com.ada.cursos.model.Rep;
import com.ada.cursos.model.Usuario;
import com.ada.cursos.repository.RepRepository;
import com.ada.cursos.repository.UsuarioRepository;


@Service
public class RepService {
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private RepRepository repRepo;
	
	
	public Rep porId(Long id) {
		
		java.util.Optional<Rep> repOp = repRepo.findById(id);
		Rep rep = repOp.get();
		
		return rep;
	}

	public Rep generarRepDeForm(RepForm repForm) {

		Rep rep = new Rep();

		java.util.Optional<Usuario> usuarioOp = usuarioRepo.findById(repForm.getId());
		Usuario usuario = usuarioOp.get();

		rep.setUsuario(usuario);
		rep.setNombre(repForm.getNombre());
		rep.setApellido(repForm.getApellido());
		rep.setTipoDoc(repForm.getTipoDoc());
		rep.setDoc(repForm.getDoc());
		rep.setCargo(repForm.getCargo());
		rep.setEmail(repForm.getEmail());
		
		return rep;

	}

}
