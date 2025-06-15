package com.daw135.dawFinalProyect.serviceImpl.admin.security;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw135.dawFinalProyect.dto.admin.UsuarioDTO;
import com.daw135.dawFinalProyect.entity.admin.Estado;
import com.daw135.dawFinalProyect.entity.admin.Sede;
import com.daw135.dawFinalProyect.entity.admin.security.Rol;
import com.daw135.dawFinalProyect.entity.admin.security.Usuario;
import com.daw135.dawFinalProyect.enums.EstadoEnum;
import com.daw135.dawFinalProyect.mapper.admin.UsuarioMapper;
import com.daw135.dawFinalProyect.repository.admin.SedeRepository;
import com.daw135.dawFinalProyect.repository.admin.security.RolRepository;
import com.daw135.dawFinalProyect.repository.admin.security.UsuarioRepository;
import com.daw135.dawFinalProyect.service.admin.security.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger logger = LogManager.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository roleRepo;

    @Autowired
    private SedeRepository sedeRepo;

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

    @Override
    public UsuarioDTO sincronizarUsuarioAuthZero(String nombre, String correo, String codigoRol) {

        try {
            Optional<Usuario> findUser = usuarioRepository.findByCorreo(correo);
            if (findUser.isPresent()) {
                Usuario user = findUser.get();
                Rol rol = roleRepo.findByCodigo(codigoRol).orElse(user.getRol());
                user.setNombre(nombre);
                user.setRol(rol);
                usuarioRepository.save(user);
                return UsuarioMapper.INSTANCE.toUsuarioDTO(user);
            } else {
                Usuario user = new Usuario();
                Rol rol = roleRepo.findByCodigo(codigoRol).orElse(null);
                Estado estado = new Estado(EstadoEnum.Activo.getCodigo());
                Sede sede = sedeRepo.findAll().stream().findFirst().orElse(null);

                user.setNombre(nombre);
                user.setCorreo(correo);
                user.setRol(rol);
                user.setEstado(estado);
                user.setSedeId(sede);
                user.setFechaCreacion(new Date());
                usuarioRepository.save(user);
                return UsuarioMapper.INSTANCE.toUsuarioDTO(user);
            }
        } catch (Exception e) {
            logger.error("Error al sincronizar usuario", e);
            return null;
        }
    }

}
