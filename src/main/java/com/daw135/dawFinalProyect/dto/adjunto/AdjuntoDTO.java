package com.daw135.dawFinalProyect.dto.adjunto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdjuntoDTO {
    private Long adjuntoId;
    private LocalDateTime fechaCreacion;
    private String url;
    private String publicId;
    private String formato;
    private String tipoArchivo; 
    private Long tamano;
    private String nombreOriginal;
}