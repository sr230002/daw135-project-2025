package com.daw135.dawFinalProyect.service.eventos;

import java.util.List;

import com.daw135.dawFinalProyect.dto.admin.EventoProgramacionDTO;

public interface EventoProgramacionService {
    String guardarEventoProgramacion(EventoProgramacionDTO dto) throws Exception;
    String editarEventoProgramacion(EventoProgramacionDTO dto) throws Exception;
    List<EventoProgramacionDTO> listarTodos();
    String eliminarEventoProgramacion(Long id);
    List<EventoProgramacionDTO> obtenerSesionesPorEventoId(Long eventoId);
}
