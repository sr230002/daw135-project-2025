package com.daw135.dawFinalProyect.serviceImpl.eventos;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw135.dawFinalProyect.dto.eventos.EventoRegistroDTO;
import com.daw135.dawFinalProyect.entity.admin.Estado;
import com.daw135.dawFinalProyect.entity.admin.EventoProgramacion;
import com.daw135.dawFinalProyect.entity.admin.Usuario;
import com.daw135.dawFinalProyect.entity.eventos.EventoRegistro;
import com.daw135.dawFinalProyect.enums.EstadoEnum;
import com.daw135.dawFinalProyect.mapper.eventos.EventoRegistroMapper;
import com.daw135.dawFinalProyect.repository.admin.UsuarioRepository;
import com.daw135.dawFinalProyect.repository.eventos.EventoProgramacionRepository;
import com.daw135.dawFinalProyect.repository.eventos.EventoRegistroRepository;
import com.daw135.dawFinalProyect.service.eventos.EventoRegistroService;

@Service
public class EventoRegistroServiceImpl implements EventoRegistroService {

    @Autowired
    private EventoRegistroRepository eventoRegistroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EventoProgramacionRepository eventoProgramacionRepository;

    @Override
    public List<EventoRegistroDTO> listarTodos() {
        return eventoRegistroRepository.findAll().stream()
                .map(EventoRegistroMapper.INSTANCE::toEventoRegistroDTO)
                .toList();
    }

    @Override
    public String guardarInscripcion(EventoRegistroDTO eventoDto) throws Exception {
        Usuario usuario = usuarioRepository.findById(eventoDto.getParticipanteId()).orElse(null);
        EventoProgramacion sesion = eventoProgramacionRepository.findById(eventoDto.getSesionId()).orElse(null);
        if (usuario == null) {
            throw new Exception("Usuario no encontrado");
        }else if (sesion == null) {
            throw new Exception("Sesión no encontrada");
        }
        EventoRegistro eventoRegistro = new EventoRegistro();
        eventoRegistro.setSesion(sesion);
        eventoRegistro.setParticipanteId(usuario);
        eventoRegistro.setFechaCreacion(new Date());
        eventoRegistro.setEstado(new Estado(EstadoEnum.Activo.getCodigo()));
        eventoRegistroRepository.save(eventoRegistro);
        return "Evento registrado con exito";
    }

    @Override
    public String editarInscripcion(EventoRegistroDTO eventoDto) throws Exception {
        EventoRegistro eventoRegistro = eventoRegistroRepository.findById(eventoDto.getEventoRegistroId()).orElse(null);
        if (eventoRegistro == null) {
            throw new Exception("Evento no encontrado");
        }
        EventoProgramacion sesion = eventoProgramacionRepository.findById(eventoDto.getSesionId()).orElse(null);
        if (sesion == null) {
            throw new Exception("Sesión no encontrada");
        }
        Usuario usuario = usuarioRepository.findById(eventoDto.getParticipanteId()).orElse(null);
        if (usuario == null) {
            throw new Exception("Usuario no encontrado");
        }
        eventoRegistro.setSesion(sesion);
        eventoRegistro.setParticipanteId(usuario);
        eventoRegistroRepository.save(eventoRegistro);
        return "Evento editado con exito";
    }

    @Override
    public EventoRegistroDTO obtenerPorId(Long id) {
        return EventoRegistroMapper.INSTANCE.toEventoRegistroDTO(eventoRegistroRepository.findById(id).orElse(new EventoRegistro()));
    }

    @Override
    public String eliminarInsCripcion(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarInsCripcion'");
    }

}
