package com.daw135.dawFinalProyect.entity.admin;

import java.time.LocalDateTime;
import java.time.LocalTime;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EMA.EVENTO_PROGRAMACION")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class EventoProgramacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evento_programacion_id")
    private Long eventoProgramacionId;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_programacion", nullable = false)
    private LocalDateTime fechaProgramacion;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    @Column(nullable = false)
    private Boolean virtual;

    @Column(nullable = false)
    private Integer cupos;

    private String lugar;
    private String enlace;

    @Column(name = "eventos_id")
    private Long eventosId;

    @Column(name = "ponente_id")
    private Long ponenteId;

    
}
