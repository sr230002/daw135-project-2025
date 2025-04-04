package com.daw135.dawFinalProyect.dto.eventos;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventoRegistroDTO {

    private Long eventoRegistroId;
    private String fechaCreacion;
    private Long sesionId;
    
    private Long participanteId;
    private String nombreParticipante;

    private String estadoId;
    private String estado;

    private String horaInicio;
    private String horaFin;
    private Boolean virtual;
    private String tituloEvento;
    private String descripcionEvento;
    private String descripcionCortaEvento;

}
