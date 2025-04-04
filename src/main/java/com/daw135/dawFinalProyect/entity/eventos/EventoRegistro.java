package com.daw135.dawFinalProyect.entity.eventos;

import java.util.Date;

import com.daw135.dawFinalProyect.entity.admin.Estado;
import com.daw135.dawFinalProyect.entity.admin.EventoProgramacion;
import com.daw135.dawFinalProyect.entity.admin.Usuario;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "ema", name = "evento_registro")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventoRegistro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evento_registro_id")
    private Long eventoRegistroId;

    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_programacion_id", referencedColumnName = "evento_programacion_id")
    private EventoProgramacion sesion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participante_id", referencedColumnName = "usuario_id")
    private Usuario participanteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado", referencedColumnName = "estado")
    private Estado estado;


}
