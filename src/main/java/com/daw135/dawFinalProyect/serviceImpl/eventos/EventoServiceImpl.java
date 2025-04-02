package com.daw135.dawFinalProyect.serviceImpl.eventos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw135.dawFinalProyect.entity.eventos.Evento;
import com.daw135.dawFinalProyect.repository.eventos.EventoRepository;
import com.daw135.dawFinalProyect.service.eventos.EventoService;

@Service
public class EventoServiceImpl implements EventoService{

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

}
