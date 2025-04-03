package com.daw135.dawFinalProyect.dto.eventos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoEventoDTO {

    private Long tipoEventoId;
    private String descripcion;

}
