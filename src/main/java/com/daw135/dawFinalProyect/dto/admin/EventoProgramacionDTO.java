package com.daw135.dawFinalProyect.dto.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
