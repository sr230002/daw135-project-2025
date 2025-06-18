package com.daw135.dawFinalProyect.repository.eventos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.daw135.dawFinalProyect.entity.eventos.EventoEvaluacion;

public interface EventoEvaluacionRepository extends JpaRepository<EventoEvaluacion, Long> {

    @Query("""
            SELECT e FROM EventoEvaluacion e
            WHERE e.evento.eventoId = :eventoId
            ORDER BY e.eventoEvaluacionId desc
        """)
    List<EventoEvaluacion> findEvaluacionesByEventoId(@Param("eventoId") Long eventoId);

}
