package com.daw135.dawFinalProyect.entity.admin;

import java.time.LocalDateTime;

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
@Table(name = "SEC.USUARIO")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String clave;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private Boolean estado;
    

    @Column(name = "rol_id")
    private Long rolId;

    @Column(name = "sede_id")
    private Long sedeId;



}
