package com.daw135.dawFinalProyect.serviceImpl.eventos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw135.dawFinalProyect.dto.eventos.EventoRegistroDTO;
import com.daw135.dawFinalProyect.mapper.eventos.EventoRegistroMapper;
import com.daw135.dawFinalProyect.repository.eventos.EventoRegistroRepository;
import com.daw135.dawFinalProyect.service.eventos.EventoRegistroService;

@Service
public class EventoRegistroServiceImpl implements EventoRegistroService {

    @Autowired
    private EventoRegistroRepository eventoRegistroRepository;

    @Override
    public List<EventoRegistroDTO> listarTodos() {
        return eventoRegistroRepository.findAll().stream()
                .map(EventoRegistroMapper.INSTANCE::toEventoRegistroDTO)
                .toList();
    }

}
