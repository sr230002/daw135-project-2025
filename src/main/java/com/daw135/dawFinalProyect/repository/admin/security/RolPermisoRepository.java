package com.daw135.dawFinalProyect.repository.admin.security;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw135.dawFinalProyect.entity.admin.security.RolPermiso;

@Repository
public interface RolPermisoRepository extends JpaRepository<RolPermiso, Long> {
    
    Optional<RolPermiso> findByRolCodigoAndPath(String rol, String path);

    List<RolPermiso> findByRolCodigo(String codigo);
}
