package com.daw135.dawFinalProyect.entity.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.daw135.dawFinalProyect.entity.eventos.Evento;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "ema", name = "evento_programacion")
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

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_programacion", nullable = false)
    private LocalDate fechaProgramacion;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    @Column(name = "virtual", nullable = false)
    private Boolean virtual;

    @Column(name = "cupos")
    private Integer cupos;

    @Column(name = "lugar", length = 250)
    private String lugar;

    @Column(name = "enlace", length = 250)
    private String enlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id", referencedColumnName = "evento_id")
    private Evento evento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ponente_id", referencedColumnName = "usuario_id")
    private Usuario ponente;

    
}
