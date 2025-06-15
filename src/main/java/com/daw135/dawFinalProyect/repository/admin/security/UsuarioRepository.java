package com.daw135.dawFinalProyect.repository.admin.security;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw135.dawFinalProyect.entity.admin.security.Rol;
import com.daw135.dawFinalProyect.entity.admin.security.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByCorreo(String correo);
    List<Usuario> findByRol(Rol rol);
}
