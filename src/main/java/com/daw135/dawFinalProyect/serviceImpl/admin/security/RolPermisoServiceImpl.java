package com.daw135.dawFinalProyect.serviceImpl.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw135.dawFinalProyect.repository.admin.security.RolPermisoRepository;
import com.daw135.dawFinalProyect.service.admin.security.RolPermisoService;

@Service
public class RolPermisoServiceImpl implements RolPermisoService {

    @Autowired
    private RolPermisoRepository rolPermisoRepository;

    @Override
    public boolean hasPermissionScreen(String role, String path) {
        try {
            return rolPermisoRepository.findByRolCodigoAndPath(role, path).isPresent();
        } catch (Exception e) {
            return false;
        }
    }

}
