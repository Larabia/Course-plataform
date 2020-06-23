package com.ada.cursos.service;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.cursos.form.RepForm;
import com.ada.cursos.model.Rep;
import com.ada.cursos.model.Usuario;
import com.ada.cursos.repository.CursoRepository;
import com.ada.cursos.repository.RepRepository;
import com.ada.cursos.repository.UsuarioRepository;


@Service
public class RepService {
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private RepRepository repRepo;
	
	Logger log = Logger.getLogger(CursoRepository.class.getName());
	
	
	public Rep porId(Long id) {
		
		Optional<Rep> repOp = repRepo.findById(id);
		
		if (Optional.empty().equals(repOp)) {
			log.info("El id ingresado no existe.");
		}
		
		Rep rep = repOp.get();
		
		return rep;
	}

	public Rep cargarDatosForm(RepForm repForm, Rep rep) {

		Optional<Usuario> usuarioOp = usuarioRepo.findById(repForm.getId());
		Usuario usuario = usuarioOp.get();

		rep.setUsuario(usuario);
		rep.setNombre(repForm.getNombre());
		rep.setApellido(repForm.getApellido());
		rep.setTipoDoc(repForm.getTipoDoc());
		rep.setDoc(repForm.getDoc());
		rep.setCargo(repForm.getCargo());
		
		
		return rep;

	}

}
