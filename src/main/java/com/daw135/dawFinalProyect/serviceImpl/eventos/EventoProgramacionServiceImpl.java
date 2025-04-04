package com.daw135.dawFinalProyect.serviceImpl.eventos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw135.dawFinalProyect.dto.admin.EventoProgramacionDTO;
import com.daw135.dawFinalProyect.entity.admin.EventoProgramacion;
import com.daw135.dawFinalProyect.mapper.eventos.EventoProgramacionMapper;
import com.daw135.dawFinalProyect.repository.eventos.EventoProgramacionRepository;
import com.daw135.dawFinalProyect.service.eventos.EventoProgramacionService;

@Service
public class EventoProgramacionServiceImpl implements EventoProgramacionService {

    @Autowired
    private EventoProgramacionRepository eventoProgramacionRepository;

    @Override
    public String guardarEventoProgramacion(EventoProgramacionDTO dto) throws Exception {
        EventoProgramacion eventoProgramacion = EventoProgramacionMapper.INSTANCE.toEventoProgramacion(dto);
        eventoProgramacionRepository.save(eventoProgramacion);
        return "Evento programado con éxito";
    }

    @Override
    public String editarEventoProgramacion(EventoProgramacionDTO dto) throws Exception {
        EventoProgramacion eventoProgramacion = EventoProgramacionMapper.INSTANCE.toEventoProgramacion(dto);
        eventoProgramacionRepository.save(eventoProgramacion);
        return "Evento programado editado con éxito";
    }

    @Override
    public List<EventoProgramacionDTO> listarTodos() {
        try {
            return eventoProgramacionRepository.findAll().stream()
                    .map(EventoProgramacionMapper.INSTANCE::toEventoProgramacionDTO)
                    .toList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public String eliminarEventoProgramacion(Long id) {
        EventoProgramacion eventoProgramacion = eventoProgramacionRepository.findById(id).orElse(null);
        if (eventoProgramacion == null) {
            return "No se encontró el evento programado";
        }
        eventoProgramacionRepository.delete(eventoProgramacion);
        return "Evento programado eliminado con éxito";
    }
}
