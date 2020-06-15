package com.ada.cursos.repository;

import org.springframework.data.repository.CrudRepository;
import com.ada.cursos.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
