package com.daw135.dawFinalProyect.entity.eventos;

import java.util.Date;

import com.daw135.dawFinalProyect.entity.admin.Estado;
import com.daw135.dawFinalProyect.entity.admin.Sede;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(schema = "ema", name = "evento")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Evento {

    @Id
    @Column(name = "evento_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventoId;
    @Column(name = "codigo", nullable = false, length = 50)
    private String codigo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;
    @Column(name = "titulo", nullable = false, length = 250)
    private String titulo;
    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;
    @Column(name = "descripcion_corta", nullable = false, length = 250)
    private String descripcionCorta;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_tipo_id", referencedColumnName = "evento_tipo_id")
    private EventoTipo eventoTipoId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado", referencedColumnName = "estado")
    private Estado estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sede_id", referencedColumnName = "sede_id")
    private Sede sedeId;

}
