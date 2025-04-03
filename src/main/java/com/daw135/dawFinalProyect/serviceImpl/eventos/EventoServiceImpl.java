package com.daw135.dawFinalProyect.serviceImpl.eventos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw135.dawFinalProyect.dto.eventos.EventoDTO;
import com.daw135.dawFinalProyect.entity.admin.Estado;
import com.daw135.dawFinalProyect.entity.admin.Sede;
import com.daw135.dawFinalProyect.entity.eventos.Evento;
import com.daw135.dawFinalProyect.entity.eventos.EventoTipo;
import com.daw135.dawFinalProyect.enums.EstadoEnum;
import com.daw135.dawFinalProyect.mapper.eventos.EventoMapper;
import com.daw135.dawFinalProyect.repository.eventos.EventoRepository;
import com.daw135.dawFinalProyect.service.eventos.EventoService;

@Service
public class EventoServiceImpl implements EventoService{
    private static final Logger logger = LogManager.getLogger(EventoServiceImpl.class);
    
    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public List<EventoDTO> findAll() {
        try {
            logger.info("Obteniendo eventos");
            return eventoRepository.findAll().stream().map(EventoMapper.INSTANCE::toEventoDTO).toList();
        } catch (Exception e) {
            logger.error("Error al obtener eventos", e);
            return new ArrayList<>();
        }
    }

    @Override
    public String guardarEvento(EventoDTO eventoDto) {
        try {
            Evento evento = EventoMapper.INSTANCE.toEvento(eventoDto);
            Estado estado = new Estado(EstadoEnum.Activo.getCodigo());
            Sede sede = new Sede(eventoDto.getSedeId());
            EventoTipo tipo = new EventoTipo(eventoDto.getTipoEventoId());

            evento.setEstado(estado);
            evento.setSedeId(sede);
            evento.setEventoTipoId(tipo);
            evento.setFechaCreacion(new Date());
            evento.setCodigo("SV-EVENTO-01");
            eventoRepository.save(evento);
            return "Evento guardado con exito";
        } catch (Exception e) {
            logger.error("Error al guardar evento", e);
            return "Error al guardar evento";
        }
    }

}
