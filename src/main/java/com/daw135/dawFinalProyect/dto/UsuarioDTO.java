package com.daw135.dawFinalProyect.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String clave;
    private LocalDateTime fechaCreacion;
    private String estado;
    private Long rolId;
    private Long sedeId;
    
}
