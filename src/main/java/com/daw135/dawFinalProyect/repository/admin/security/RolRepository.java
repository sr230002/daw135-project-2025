package com.daw135.dawFinalProyect.repository.admin.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw135.dawFinalProyect.entity.admin.security.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {

    Optional<Rol> findByCodigo(String codigo);

}
