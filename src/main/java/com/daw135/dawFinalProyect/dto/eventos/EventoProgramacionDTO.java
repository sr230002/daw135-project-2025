package com.daw135.dawFinalProyect.dto.eventos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventoProgramacionDTO {
    private Long eventoProgramacionId;
    private String fechaCreacion;
    private String fechaProgramacion;
    private String horaInicio;
    private String horaFin;
    private Boolean virtual;
    private Integer cupos;
    private String lugar;
    private String enlace;
    private Long eventoId;
    private String eventoTitulo;
    private String eventoDescripcion;
    private String descripcion; // sirve para concatenar feha, y horas

    private Long ponenteId;
    private String ponenteNombre;
    private String ponenteCorreo;

    private List<EventoRegistroDTO> inscripciones;
}
