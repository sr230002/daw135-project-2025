package com.daw135.dawFinalProyect.entity.admin;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "sec", name = "sede")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Sede {

    @Id
    @Column(name = "sede_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sedeId;
    @Column(name = "codigo", nullable = false, length = 50)
    private String codigo;
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;
    @Column(name = "telefono", nullable = false, length = 100)
    private String telefono;
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institucion_id", referencedColumnName = "institucion_id")
    private Institucion institucion;

}
