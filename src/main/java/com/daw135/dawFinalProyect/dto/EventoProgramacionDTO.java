package com.daw135.dawFinalProyect.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventoProgramacionDTO {
    private Long id;
    private LocalDate fechaCreacio;
    private LocalDate fechaProgramacion;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Boolean virtual; 
    private Integer cupos;
    private String lugar;
    private String enlace;
    private Long eventosId;
    private Long ponenteId;

}
