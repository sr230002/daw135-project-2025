package com.daw135.dawFinalProyect.serviceImpl.eventos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.daw135.dawFinalProyect.config.auth.AuthUtils;
import com.daw135.dawFinalProyect.dto.eventos.EventoDTO;
import com.daw135.dawFinalProyect.dto.eventos.EventoProgramacionDTO;
import com.daw135.dawFinalProyect.dto.eventos.EventoRegistroDTO;
import com.daw135.dawFinalProyect.entity.adjunto.Adjunto;
import com.daw135.dawFinalProyect.entity.admin.Estado;
import com.daw135.dawFinalProyect.entity.admin.Sede;
import com.daw135.dawFinalProyect.entity.eventos.Evento;
import com.daw135.dawFinalProyect.entity.eventos.EventoTipo;
import com.daw135.dawFinalProyect.enums.AsistenciaEnum;
import com.daw135.dawFinalProyect.enums.EstadoEnum;
import com.daw135.dawFinalProyect.mapper.eventos.EventoMapper;
import com.daw135.dawFinalProyect.mapper.eventos.EventoProgramacionMapper;
import com.daw135.dawFinalProyect.mapper.eventos.EventoRegistroMapper;
import com.daw135.dawFinalProyect.repository.admin.SedeRepository;
import com.daw135.dawFinalProyect.repository.eventos.EventoProgramacionRepository;
import com.daw135.dawFinalProyect.repository.eventos.EventoRegistroRepository;
import com.daw135.dawFinalProyect.repository.eventos.EventoRepository;
import com.daw135.dawFinalProyect.repository.eventos.TipoEventoRepository;
import com.daw135.dawFinalProyect.service.adjunto.AdjuntoService;
import com.daw135.dawFinalProyect.service.eventos.EventoService;

@Service
public class EventoServiceImpl implements EventoService {
    private static final Logger logger = LogManager.getLogger(EventoServiceImpl.class);

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private TipoEventoRepository eventoTipoRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private EventoProgramacionRepository eventoProgramacionRepository;

    @Autowired
    private EventoRegistroRepository eventoRegistroRepository;

    @Autowired
    private AdjuntoService adjuntoService;

    @Override
    public List<EventoDTO> findAll() {
        try {
            return eventoRepository.findAll().stream().map(EventoMapper.INSTANCE::toEventoDTO).toList();
        } catch (Exception e) {
            logger.error("Error al obtener eventos", e);
            return new ArrayList<>();
        }
    }

    @Override
    public String guardarEvento(EventoDTO dto, MultipartFile imagenFile) throws Exception {
        dto.setFechaCreacion(null);
        Evento evento = EventoMapper.INSTANCE.toEvento(dto);
        Estado estado = new Estado(EstadoEnum.Activo.getCodigo());
        Sede sede = sedeRepository.findById(dto.getSedeId()).orElse(null);
        EventoTipo tipo = eventoTipoRepository.findById(dto.getTipoEventoId()).orElse(null);

        if (sede == null) {
            throw new Exception("Sede no encontrada");
        }
        if (tipo == null) {
            throw new Exception("Tipo de evento no encontrado");
        }
        if (imagenFile == null) {
            throw new Exception("La imagen es obligatoria");
        }

        Optional<Adjunto> adjunto = adjuntoService.uploadFile(imagenFile);
        if (adjunto.isEmpty()) {
            throw new Exception("Error al subir adjunto");
        }
        
        evento.setAdjunto(adjunto.get());
        evento.setEstado(estado);
        evento.setSedeId(sede);
        evento.setEventoTipoId(tipo);
        evento.setFechaCreacion(new Date());
        eventoRepository.save(evento);
        return "Evento guardado con exito";
    }

    @Override
    public String editarEvento(EventoDTO dto, MultipartFile newAdjunto) throws Exception {
        Evento evento = EventoMapper.INSTANCE.toEvento(dto);
        Sede sede = sedeRepository.findById(dto.getSedeId()).orElse(null);
        EventoTipo tipo = eventoTipoRepository.findById(dto.getTipoEventoId()).orElse(null);
        Estado estado = new Estado(dto.getEstadoId());

        if (sede == null) {
            throw new Exception("Sede no encontrada");
        }
        if (tipo == null) {
            throw new Exception("Tipo de evento no encontrado");
        }

        Adjunto currentAdjunto = eventoRepository.findById(dto.getEventoId()).map(Evento::getAdjunto).orElse(null);
        if (newAdjunto != null && !newAdjunto.isEmpty()) {

            //primero elimino el adjunto actual, si es que tiene
            if (currentAdjunto != null) {
                adjuntoService.deleteFile(currentAdjunto.getAdjuntoId());
            }
            // despues se sube el nuevo adjunto
            adjuntoService.uploadFile(newAdjunto).ifPresent(evento::setAdjunto);
        }


        evento.setSedeId(sede);
        evento.setEventoTipoId(tipo);
        evento.setEstado(estado);
        eventoRepository.save(evento);
        return "Evento editado con exito";
    }

    @Override
    public EventoDTO obtenerPorId(Long id) {
        return EventoMapper.INSTANCE.toEventoDTO(eventoRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public String eliminarEvento(Long id) {
        try {
            Evento evento = eventoRepository.findById(id).orElse(null);
            if (evento == null) {
                return "No se encontro el evento";
            }
            eventoProgramacionRepository.deleteByEvento(evento);
            eventoRepository.delete(evento);
            eventoProgramacionRepository.flush();
            eventoRepository.flush();
            return "Evento eliminado con exito";
        } catch (DataIntegrityViolationException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "No se puede eliminar el evento porque tiene participantes registrados";
        } catch (Exception e) {
            logger.warn("Error al eliminar el evento");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "Error al eliminar el evento";
        }
    }

    @Override
    public List<EventoDTO> findAllMisEventos() {
        return AuthUtils.getEmail().map(email -> eventoRepository.findEventosByParticipanteCorreo(email).stream()
                .map(EventoMapper.INSTANCE::toEventoDTO)
                .toList()).orElse(Collections.emptyList());
    }

    @Override
    public EventoDTO obtenerEventoInformacionByEventoIdAndCorreo(Long eventoId) {
        String email = AuthUtils.getEmail().orElse(null);
        return eventoRepository.findById(eventoId)
                .map(evento -> {
                    EventoDTO eventoDTO = EventoMapper.INSTANCE.toEventoDTO(evento);

                    List<EventoProgramacionDTO> sesiones = eventoProgramacionRepository
                            .findByEventoIdAndParticipanteCorreo(evento.getEventoId(), email).stream()
                            .map(sesion -> {
                                EventoProgramacionDTO sesionDTO = EventoProgramacionMapper.INSTANCE
                                        .toEventoProgramacionDTO(sesion);

                                List<EventoRegistroDTO> inscripciones = eventoRegistroRepository
                                        .findBySesionIdAndParticipanteCorreo(sesion.getEventoProgramacionId(), email)
                                        .stream()
                                        .map(EventoRegistroMapper.INSTANCE::toEventoRegistroDTO)
                                        .toList();

                                sesionDTO.setInscripciones(inscripciones);
                                return sesionDTO;
                            })
                            .toList();

                    eventoDTO.setSesiones(sesiones);
                    return eventoDTO;
                })
                .orElse(null);
    }

    @Override
    public boolean marcarAsistencia(Long eventoRegistroId) {
        return eventoRegistroRepository.findById(eventoRegistroId).map(eventoRegistro -> {
            eventoRegistro.setAsistencia(AsistenciaEnum.Presente.getCodigo());
            eventoRegistroRepository.save(eventoRegistro);
            return true;
        }).orElse(false);
    }

    @Override
    public List<EventoDTO> findEventosDisponibles() {
        return AuthUtils.getEmail().map(email -> eventoRepository.findEventosDisponibles(email).stream()
                .map(EventoMapper.INSTANCE::toEventoDTO)
                .toList()).orElse(Collections.emptyList());
    }

}
