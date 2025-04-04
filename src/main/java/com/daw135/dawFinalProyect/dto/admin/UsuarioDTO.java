package com.daw135.dawFinalProyect.dto.admin;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class UsuarioDTO {
    private Long usuarioId;
    private String nombre;
    private String correo;
    private String clave;
    private String estado;
    private Long rolId;
    private Long sedeId;
    private LocalDateTime fechaCreacion;
}
