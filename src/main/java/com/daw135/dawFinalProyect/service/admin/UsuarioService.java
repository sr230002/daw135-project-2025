package com.daw135.dawFinalProyect.service.admin;

import java.util.List;

import com.daw135.dawFinalProyect.dto.admin.UsuarioDTO;

public interface UsuarioService {
    List<UsuarioDTO> listarTodos();
    UsuarioDTO obtenerPorId(Long id);
    String guardarUsuario(UsuarioDTO usuarioDTO);
    String eliminarUsuario(Long id);
}
