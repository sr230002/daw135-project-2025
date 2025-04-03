package com.daw135.dawFinalProyect.entity.eventos;

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
@Table(schema = "ema", name = "evento_tipo")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventoTipo {
    @Id
    @Column(name = "evento_tipo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventoTipoId;
    @Column(name = "descripcion", nullable = false, length = 250)
    private String descripcion;

    public EventoTipo(Long eventoTipoId) {
        this.eventoTipoId = eventoTipoId;
    }
}
