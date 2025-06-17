package com.daw135.dawFinalProyect.enums;

public enum AsistenciaEnum {
    Presente("PST"),
    InasistenciaNoJustificada("INJ"),
    InasistenciaSiJustificada("ISJ");

    private final String codigo;

    AsistenciaEnum(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

}
