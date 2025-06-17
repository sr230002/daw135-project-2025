package com.daw135.dawFinalProyect.mapper.adjunto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.daw135.dawFinalProyect.dto.adjunto.AdjuntoDTO;
import com.daw135.dawFinalProyect.dto.adjunto.EventoAdjuntoDTO;
import com.daw135.dawFinalProyect.entity.adjunto.Adjunto;
import com.daw135.dawFinalProyect.entity.eventos.EventoAdjunto;

@Mapper
public interface AdjuntoMapper {

    AdjuntoMapper INSTANCE = Mappers.getMapper(AdjuntoMapper.class);

    // AdjuntoDTO to Adjunto
    @Mapping(target = "adjuntoId", source = "adjuntoId")
    @Mapping(target = "fechaCreacion", source = "fechaCreacion")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "publicId", source = "publicId")
    @Mapping(target = "tipoArchivo", source = "tipoArchivo")
    @Mapping(target = "formato", source = "formato")
    @Mapping(target = "tamano", source = "tamano")
    @Mapping(target = "nombreOriginal", source = "nombreOriginal")
    @Mapping(target = "eventos", ignore = true)
    Adjunto toAdjunto(AdjuntoDTO dto);

    // Adjunto to AdjuntoDTO
    @Mapping(target = "adjuntoId", source = "adjuntoId")
    @Mapping(target = "fechaCreacion", source = "fechaCreacion")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "publicId", source = "publicId")
    @Mapping(target = "tipoArchivo", source = "tipoArchivo")
    @Mapping(target = "formato", source = "formato")
    @Mapping(target = "tamano", source = "tamano")
    @Mapping(target = "nombreOriginal", source = "nombreOriginal")
    AdjuntoDTO toAdjuntoDTO(Adjunto adjunto);

    // convertir de EventoAdjunto a EventoAdjuntoDTO
    @Mapping(target = "eventoAdjuntoId", source = "eventoAdjuntoId")
    @Mapping(target = "adjuntoId", source = "adjunto.adjuntoId")
    @Mapping(target = "url", source = "adjunto.url")
    @Mapping(target = "nombre", source = "adjunto.nombreOriginal")
    @Mapping(target = "eventoId", source = "evento.eventoId")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "visible", source = "visible")
    @Mapping(target = "tipo", source = "adjunto.tipoArchivo")
    @Mapping(target = "formato", source = "adjunto.formato")
    EventoAdjuntoDTO toEventoAdjuntoDTO(EventoAdjunto eventoAdjunto);

    // convertir de EventoAdjuntoDTO a EventoAdjunto
    @Mapping(target = "eventoAdjuntoId", source = "eventoAdjuntoId")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "visible", source = "visible")
    @Mapping(target = "adjunto", ignore = true)
    @Mapping(target = "evento", ignore = true)
    EventoAdjunto toEventoAdjunto(EventoAdjuntoDTO dto);

}
