package com.daw135.dawFinalProyect.mapper.eventos;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.daw135.dawFinalProyect.dto.admin.EventoProgramacionDTO;
import com.daw135.dawFinalProyect.entity.admin.EventoProgramacion;

@Mapper
public interface EventoProgramacionMapper {

    EventoProgramacionMapper INSTANCE = Mappers.getMapper(EventoProgramacionMapper.class);

    @Mapping(target = "eventoId", source = "eventosId.eventoId")
    @Mapping(target = "ponenteId", source = "ponenteId.usuarioId")
    @Mapping(target = "fechaCreacion", source = "fechaCreacion") // Agregado
    EventoProgramacionDTO toEventoProgramacionDTO(EventoProgramacion eventoProgramacion);

    @Mapping(target = "eventosId.eventoId", source = "eventoId")
    @Mapping(target = "ponenteId.usuarioId", source = "ponenteId")
    @Mapping(target = "fechaCreacion", source = "fechaCreacion") // Agregado
    EventoProgramacion toEventoProgramacion(EventoProgramacionDTO eventoProgramacionDTO);
}
