package com.daw135.dawFinalProyect.enums;

public enum EstadoEnum {
    Activo("ACT"), 
    Inactivo("INA"),
    Finalizado("FIN"),
    Suspendido("SUS"),
    Cancelado("CNC"),
    Inscrito("INS"),
    Confirmado("CFM");

    private final String codigo;

    EstadoEnum(String codigo) {
        this.codigo = codigo;
    }
    public String getCodigo() {
        return codigo;
    }
}
