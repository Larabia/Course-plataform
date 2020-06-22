package com.ada.cursos.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ada.cursos.model.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUserName(String nu);
    boolean existsByUserName(String nu);
    boolean existsByEmail(String email);
}