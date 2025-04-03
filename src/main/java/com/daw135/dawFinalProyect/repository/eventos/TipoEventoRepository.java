package com.daw135.dawFinalProyect.repository.eventos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw135.dawFinalProyect.entity.eventos.EventoTipo;

public interface TipoEventoRepository extends JpaRepository<EventoTipo, Long> {

}
