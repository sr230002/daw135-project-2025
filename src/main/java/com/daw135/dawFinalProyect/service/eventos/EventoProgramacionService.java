package com.daw135.dawFinalProyect.service.eventos;

import java.util.List;
import java.util.Optional;

import com.daw135.dawFinalProyect.dto.eventos.EventoProgramacionDTO;

public interface EventoProgramacionService {

    public Optional<EventoProgramacionDTO> findById(Long id);
    public String guardarEventoProgramacion(EventoProgramacionDTO dto) throws Exception;
    public String editarEventoProgramacion(EventoProgramacionDTO dto) throws Exception;
    public List<EventoProgramacionDTO> listarTodos();
    public String eliminarEventoProgramacion(Long id);
    public List<EventoProgramacionDTO> obtenerSesionesPorEventoId(Long eventoId);
}
