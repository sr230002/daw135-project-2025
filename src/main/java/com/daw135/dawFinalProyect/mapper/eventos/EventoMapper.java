package com.daw135.dawFinalProyect.mapper.eventos;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.daw135.dawFinalProyect.dto.eventos.EventoDTO;
import com.daw135.dawFinalProyect.entity.eventos.Evento;

@Mapper
public interface EventoMapper {

    EventoMapper INSTANCE = Mappers.getMapper(EventoMapper.class);

    @Mapping(target = "eventoId", source = "eventoId")
    @Mapping(target = "codigo", source = "codigo")
    @Mapping(target = "fechaCreacion", source = "fechaCreacion", dateFormat = "dd/MM/yyyy")
    @Mapping(target = "titulo", source = "titulo")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "descripcionCorta", source = "descripcionCorta")
    @Mapping(target = "tipoEventoId", source = "eventoTipoId.eventoTipoId")
    @Mapping(target = "tipoEvento", source = "eventoTipoId.descripcion")
    @Mapping(target = "estadoId", source = "estado.estado")
    @Mapping(target = "estado", source = "estado.descripcion")
    @Mapping(target = "sedeId", source = "sedeId.sedeId")
    @Mapping(target = "sedeNombre", source = "sedeId.nombre")
    EventoDTO toEventoDTO(Evento evento); 

}
