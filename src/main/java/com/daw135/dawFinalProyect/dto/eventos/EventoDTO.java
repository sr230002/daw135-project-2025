
package com.daw135.dawFinalProyect.dto.eventos;

import java.util.List;

import com.daw135.dawFinalProyect.dto.adjunto.AdjuntoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO {
    private Long eventoId;
    private String codigo;
    private String fechaCreacion;
    private String fechaInicio;
    private String fechaFin;
    private String titulo;
    private String descripcion;
    private String descripcionCorta;
    private Long tipoEventoId;
    private String tipoEvento;
    private String estadoId;
    private String estado;
    private Long sedeId;
    private String sedeNombre;

    private Long adjuntoId;
    private String urlImagen;

    private List<EventoProgramacionDTO> sesiones;
    private List<AdjuntoDTO> adjuntos;
}
