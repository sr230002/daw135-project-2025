package com.daw135.dawFinalProyect.service.eventos;

import java.util.List;

import com.daw135.dawFinalProyect.dto.eventos.EventoDTO;

public interface EventoService {

    List<EventoDTO> findAll();
    String guardarEvento(EventoDTO eventoDto) throws Exception ;
    String editarEvento(EventoDTO eventoDto) throws Exception ;
    EventoDTO obtenerPorId(Long id);
    String eliminarEvento(Long id);

}
