package com.daw135.dawFinalProyect.service.eventos;

import java.util.List;

import com.daw135.dawFinalProyect.dto.eventos.EventoRegistroDTO;

public interface EventoRegistroService {


    List<EventoRegistroDTO> listarTodos();
    String guardarInscripcion(EventoRegistroDTO eventoDto) throws Exception ;
    String editarInscripcion(EventoRegistroDTO eventoDto) throws Exception ;
    EventoRegistroDTO obtenerPorId(Long id);
    String eliminarInscripcion(Long id);

}
