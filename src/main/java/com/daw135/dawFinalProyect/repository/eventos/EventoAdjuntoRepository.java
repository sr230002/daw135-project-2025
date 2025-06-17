package com.daw135.dawFinalProyect.repository.eventos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw135.dawFinalProyect.entity.eventos.EventoAdjunto;

@Repository
public interface EventoAdjuntoRepository extends JpaRepository<EventoAdjunto, Long> {

}
