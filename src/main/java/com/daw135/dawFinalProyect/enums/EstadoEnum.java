package com.daw135.dawFinalProyect.enums;

public enum EstadoEnum {
    Activo("ACT"), 
    Inactivo("INA"),
    Finalizado("FIN"),
    Suspendido("SUS"),
    Cancelado("CNC");

    private final String codigo;

    EstadoEnum(String codigo) {
        this.codigo = codigo;
    }
    public String getCodigo() {
        return codigo;
    }
}
