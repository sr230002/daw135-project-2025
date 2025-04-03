package com.daw135.dawFinalProyect.serviceImpl.eventos;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw135.dawFinalProyect.dto.eventos.EventoDTO;
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

}
