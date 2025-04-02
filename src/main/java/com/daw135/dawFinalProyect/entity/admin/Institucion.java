package com.daw135.dawFinalProyect.entity.admin;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "sec", name = "institucion")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Institucion {
    @Id
    @Column(name = "institucion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long institucionId;
    @Column(name = "codigo", nullable = false, length = 50)
    private String codigo;
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
}
