package com.daw135.dawFinalProyect.dto.eventos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    private LocalDateTime fechaCreacion; 
    private LocalDate fechaProgramacion;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Boolean virtual;
    private Integer cupos;
    private String lugar;
    private String enlace;
    private Long eventoId;
    private Long ponenteId;
    private String descripcion;
}
