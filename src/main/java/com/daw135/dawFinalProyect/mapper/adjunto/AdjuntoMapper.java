package com.daw135.dawFinalProyect.mapper.adjunto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.daw135.dawFinalProyect.dto.adjunto.AdjuntoDTO;
import com.daw135.dawFinalProyect.entity.adjunto.Adjunto;

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

}
