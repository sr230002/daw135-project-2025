package com.daw135.dawFinalProyect.dto.adjunto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventoAdjuntoDTO {

    private Long eventoAdjuntoId;
    private Long adjuntoId;
    private Long eventoId;
    private String url;
    private String tipo;
    private String formato;
    private String nombre;
    private String descripcion;
    private Boolean visible;
}
