package com.daw135.dawFinalProyect.repository.eventos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw135.dawFinalProyect.entity.admin.EventoProgramacion;
import com.daw135.dawFinalProyect.entity.eventos.Evento;

@Repository
public interface EventoProgramacionRepository extends JpaRepository<EventoProgramacion, Long> {

    List<EventoProgramacion> findByEventoEventoId(Long eventoId);
    void deleteByEvento(Evento evento);
}
