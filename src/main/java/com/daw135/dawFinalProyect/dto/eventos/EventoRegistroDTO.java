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
public class EventoRegistroDTO {

    private Long eventoRegistroId;
    private String fechaCreacion;
    private Long sesionId;
    
    private Long participanteId;
    private String participanteNombre;
    private String participanteCorreo;

    private String estadoId;
    private String estadoDesc;
    
    private String asistencia;
    private Boolean asistenciaConfirmada;

    private String fechaProgramacion;
    private String horaInicio;
    private String horaFin;
    private Boolean virtual;
    private String tituloEvento;
    private String descripcionEvento;
    private String descripcionCortaEvento;
    private Long eventoId;


}
