package com.daw135.dawFinalProyect.serviceImpl.eventos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*import org.hibernate.exception.ConstraintViolationException;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.daw135.dawFinalProyect.dto.eventos.EventoDTO;
import com.daw135.dawFinalProyect.entity.admin.Estado;
import com.daw135.dawFinalProyect.entity.admin.EventoProgramacion;
import com.daw135.dawFinalProyect.entity.admin.Sede;
import com.daw135.dawFinalProyect.entity.eventos.Evento;
import com.daw135.dawFinalProyect.entity.eventos.EventoTipo;
import com.daw135.dawFinalProyect.enums.EstadoEnum;
import com.daw135.dawFinalProyect.helpers.DawUtil;
import com.daw135.dawFinalProyect.mapper.eventos.EventoMapper;
import com.daw135.dawFinalProyect.repository.admin.SedeRepository;
import com.daw135.dawFinalProyect.repository.eventos.EventoProgramacionRepository;
import com.daw135.dawFinalProyect.repository.eventos.EventoRepository;
import com.daw135.dawFinalProyect.repository.eventos.TipoEventoRepository;
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
    public String guardarEvento(EventoDTO dto) throws Exception {
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

        evento.setEstado(estado);
        evento.setSedeId(sede);
        evento.setEventoTipoId(tipo);
        evento.setFechaCreacion(new Date());
        Evento eventoCreado = eventoRepository.save(evento);

        EventoProgramacion sesion = new EventoProgramacion();
        LocalDate fechaProgramacion = DawUtil.dateToLocalDate(eventoCreado.getFechaInicio());
        LocalTime horaInicio = DawUtil.stringToLocalTime("10:00");
        LocalTime horaFin = DawUtil.stringToLocalTime("12:00");

        sesion.setEvento(eventoCreado);
        sesion.setPonente(null);
        sesion.setFechaProgramacion(fechaProgramacion);
        sesion.setHoraInicio(horaInicio);
        sesion.setHoraFin(horaFin);
        sesion.setCupos(25);
        sesion.setLugar(null);
        sesion.setEnlace(null);
        sesion.setVirtual(Boolean.TRUE);
        eventoProgramacionRepository.save(sesion);
        return "Evento guardado con exito";
    }

    @Override
    public String editarEvento(EventoDTO dto) throws Exception {
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

}
