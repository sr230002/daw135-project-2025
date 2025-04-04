package com.daw135.dawFinalProyect.mapper.eventos;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.daw135.dawFinalProyect.dto.eventos.EventoRegistroDTO;
import com.daw135.dawFinalProyect.entity.eventos.EventoRegistro;

@Mapper
public interface EventoRegistroMapper {

    EventoRegistroMapper INSTANCE = Mappers.getMapper(EventoRegistroMapper.class);

    @Mapping(target = "eventoRegistroId", source = "eventoRegistroId")
    @Mapping(target = "fechaCreacion", source = "fechaCreacion")
    @Mapping(target = "sesionId", source = "sesion.eventoProgramacionId")
    @Mapping(target = "participanteId", source = "participanteId.usuarioId")
    @Mapping(target = "estado", source = "estado.estado")
    EventoRegistroDTO toEventoRegistroDTO(EventoRegistro eventoRegistro);



}
