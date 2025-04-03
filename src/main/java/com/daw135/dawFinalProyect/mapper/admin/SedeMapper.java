package com.daw135.dawFinalProyect.mapper.admin;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.daw135.dawFinalProyect.dto.admin.SedeDTO;
import com.daw135.dawFinalProyect.entity.admin.Sede;

@Mapper
public interface SedeMapper {
    SedeMapper INSTANCE = Mappers.getMapper(SedeMapper.class);

    @Mapping(target = "sedeId", source = "sedeId")
    @Mapping(target = "nombre", source = "nombre")
    SedeDTO toSedeDTO(Sede sede);
}
