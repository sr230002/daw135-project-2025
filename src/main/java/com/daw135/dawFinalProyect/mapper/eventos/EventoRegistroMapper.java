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
    @Mapping(target = "fechaCreacion", source = "fechaCreacion", dateFormat = "dd/MM/yyyy")
    @Mapping(target = "sesionId", source = "sesion.eventoProgramacionId")
    @Mapping(target = "participanteId", source = "participanteId.usuarioId")
    @Mapping(target = "estadoId", source = "estado.estado")
    @Mapping(target = "estado", source = "estado.descripcion")
    @Mapping(target = "nombreParticipante", source = "participanteId.nombre")
    @Mapping(target = "fechaProgramacion", source = "sesion.fechaProgramacion", dateFormat = "dd/MM/yyyy")
    @Mapping(target = "horaInicio", source = "sesion.horaInicio")
    @Mapping(target = "horaFin", source = "sesion.horaFin")
    @Mapping(target = "virtual", source = "sesion.virtual")
    @Mapping(target = "tituloEvento", source = "sesion.evento.titulo")
    @Mapping(target = "descripcionEvento", source = "sesion.evento.descripcion")
    @Mapping(target = "descripcionCortaEvento", source = "sesion.evento.descripcionCorta")
    @Mapping(target = "eventoId", source = "sesion.evento.eventoId")
    EventoRegistroDTO toEventoRegistroDTO(EventoRegistro eventoRegistro);
    
}
