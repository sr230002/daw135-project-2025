package com.daw135.dawFinalProyect.mapper.eventos;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.daw135.dawFinalProyect.dto.eventos.EventoProgramacionDTO;
import com.daw135.dawFinalProyect.entity.admin.EventoProgramacion;
import com.daw135.dawFinalProyect.helpers.DawUtil;

@Mapper
public interface EventoProgramacionMapper {

    EventoProgramacionMapper INSTANCE = Mappers.getMapper(EventoProgramacionMapper.class);

    @Mapping(target = "eventoId", source = "evento.eventoId")
    @Mapping(target = "ponenteId", source = "ponente.usuarioId")
    @Mapping(target = "fechaCreacion", source = "fechaCreacion") 
    @Mapping(target = "descripcion", ignore = true)
    EventoProgramacionDTO toEventoProgramacionDTO(EventoProgramacion eventoProgramacion);
    
    @Mapping(target = "evento.eventoId", source = "eventoId")
    @Mapping(target = "ponente.usuarioId", source = "ponenteId")
    @Mapping(target = "fechaCreacion", source = "fechaCreacion") // Agregado
    EventoProgramacion toEventoProgramacion(EventoProgramacionDTO eventoProgramacionDTO);
    
    @Mapping(target = "eventoProgramacionId", source = "eventoProgramacionId")
    @Mapping(target = "descripcion", expression = "java(formatDescripcion(eventoProgramacion))")
    @Mapping(target = "eventoId", ignore = true)
    @Mapping(target = "ponenteId", ignore = true)
    EventoProgramacionDTO toEventoProgramacionDTOCmb(EventoProgramacion eventoProgramacion);

    default String formatDescripcion(EventoProgramacion eventoProgramacion) {
        return ( eventoProgramacion.getFechaProgramacion().format(DawUtil.dateTimeFormatter) )+ " " +  
               eventoProgramacion.getHoraInicio() + " - " +
               eventoProgramacion.getHoraFin();
    }
}
