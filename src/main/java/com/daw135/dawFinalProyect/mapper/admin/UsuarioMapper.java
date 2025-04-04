package com.daw135.dawFinalProyect.mapper.admin;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.daw135.dawFinalProyect.dto.admin.UsuarioDTO;
import com.daw135.dawFinalProyect.entity.admin.Estado;
import com.daw135.dawFinalProyect.entity.admin.Sede;
import com.daw135.dawFinalProyect.entity.admin.Usuario;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(target = "estado", source = "estado", qualifiedByName = "estadoToString") // Map Estado -> String
    @Mapping(target = "sedeId", source = "sedeId", qualifiedByName = "sedeToLong") // Map Sede -> Long
    @Mapping(target = "fechaCreacion", source = "fechaCreacion") // Map fechaCreacion
    UsuarioDTO toUsuarioDTO(Usuario usuario);

    @Mapping(target = "estado", source = "estado", qualifiedByName = "stringToEstado") // Map String -> Estado
    @Mapping(target = "sedeId", source = "sedeId", qualifiedByName = "longToSede") // Map Long -> Sede
    @Mapping(target = "fechaCreacion", source = "fechaCreacion") // Map fechaCreacion
    Usuario toUsuario(UsuarioDTO usuarioDTO);

    // Métodos personalizados para mapear Estado y Sede
    @Named("estadoToString")
    default String estadoToString(Estado estado) {
        return estado != null ? estado.getEstado() : null;
    }

    @Named("stringToEstado")
    default Estado stringToEstado(String estado) {
        return estado != null ? new Estado(estado) : null;
    }

    @Named("sedeToLong")
    default Long sedeToLong(Sede sede) {
        return sede != null ? sede.getSedeId() : null;
    }

    @Named("longToSede")
    default Sede longToSede(Long sedeId) {
        return sedeId != null ? new Sede(sedeId) : null;
    }
}
