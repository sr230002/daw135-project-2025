package com.daw135.dawFinalProyect.entity.adjunto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.daw135.dawFinalProyect.entity.eventos.Evento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(schema = "ema", name = "adjunto")
public class Adjunto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adjunto_id")
    private Long adjuntoId;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
    @Column(name = "url", nullable = false, length = 250)
    private String url;
    @Column(name = "public_id", nullable = false, length = 100)
    private String publicId;
    @Column(name = "tipo_archivo", nullable = false, length = 20)
    private String tipoArchivo;
    @Column(name = "formato", nullable = false, length = 50)
    private String formato;
    @Column(name = "tamano", nullable = false)
    private Long tamano;
    @Column(name = "nombre_original", nullable = false, length = 150)
    private String nombreOriginal;

    @OneToMany(mappedBy = "adjunto", fetch = FetchType.LAZY)
    private List<Evento> eventos = new ArrayList<>();

}