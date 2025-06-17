package com.daw135.dawFinalProyect.service.eventos;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.daw135.dawFinalProyect.dto.eventos.EventoDTO;

public interface EventoService {

    public List<EventoDTO> findAll();

    public String guardarEvento(EventoDTO eventoDto, MultipartFile imagenFile) throws Exception;

    public String editarEvento(EventoDTO eventoDto, MultipartFile imagenFile) throws Exception;

    public EventoDTO obtenerPorId(Long id);

    public String eliminarEvento(Long id);

    /* MIS EVENTOS */
    public List<EventoDTO> findAllMisEventos();

    public EventoDTO obtenerEventoInformacionByEventoIdAndCorreo(Long eventoId);

    public boolean marcarAsistencia(Long eventoRegistroId);

    public List<EventoDTO> findEventosDisponibles();

}
