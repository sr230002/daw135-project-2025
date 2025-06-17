package com.daw135.dawFinalProyect.entity.eventos;

import com.daw135.dawFinalProyect.entity.adjunto.Adjunto;

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

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "evento_adjunto", schema = "ema")
public class EventoAdjunto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evento_adjunto_id")
    private Long eventoAdjuntoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id", referencedColumnName = "evento_id", nullable = false)
    private Evento evento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adjunto_id", referencedColumnName = "adjunto_id", nullable = false)
    private Adjunto adjunto;

    @Column(name = "descripcion", nullable = true, length = 500)
    private String descripcion;

    @Column(name = "visible", nullable = false)
    private Boolean visible = true;

}
