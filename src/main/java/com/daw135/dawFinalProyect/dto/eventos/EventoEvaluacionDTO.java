package com.daw135.dawFinalProyect.dto.eventos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventoEvaluacionDTO {

    private Long eventoEvaluacionId;
    private Long eventoId;
    private Long usuarioId;
    private String usuarioNombre;
    private String usuarioCorreo;
    private Integer calificacion;
    private String comentario;
    private String fecha;

}
