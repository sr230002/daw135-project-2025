package com.daw135.dawFinalProyect.entity.admin;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "sec", name = "estado")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Estado {

    @Id
    @Column(name = "estado")
    private String estado;
    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    public Estado(String estado) {
        this.estado = estado;
    }

}
