package com.daw135.dawFinalProyect.serviceImpl.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw135.dawFinalProyect.dto.admin.UsuarioDTO;
import com.daw135.dawFinalProyect.entity.admin.Usuario;
import com.daw135.dawFinalProyect.mapper.admin.UsuarioMapper;
import com.daw135.dawFinalProyect.repository.admin.UsuarioRepository;
import com.daw135.dawFinalProyect.service.admin.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioMapper.INSTANCE::toUsuarioDTO)
                .toList();
    }

    @Override
    public UsuarioDTO obtenerPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(UsuarioMapper.INSTANCE::toUsuarioDTO).orElse(null);
    }

    @Override
    public String guardarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioMapper.INSTANCE.toUsuario(usuarioDTO);
        usuarioRepository.save(usuario);
        return "Usuario guardado con éxito";
    }

    @Override
    public String eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return "Usuario eliminado con éxito";
        }
        return "Usuario no encontrado";
    }
}
