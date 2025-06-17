package com.daw135.dawFinalProyect.repository.eventos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daw135.dawFinalProyect.entity.admin.EventoProgramacion;
import com.daw135.dawFinalProyect.entity.eventos.Evento;

@Repository
public interface EventoProgramacionRepository extends JpaRepository<EventoProgramacion, Long> {

    public List<EventoProgramacion> findByEventoEventoId(Long eventoId);

    public List<EventoProgramacion> findByEvento(Evento evento);

    @Query("""
                SELECT DISTINCT ep
                FROM EventoRegistro er
                JOIN er.sesion ep
                JOIN er.participanteId p
                WHERE ep.evento.eventoId = :eventoId
                AND LOWER(p.correo) = LOWER(:correo)
            """)
    public List<EventoProgramacion> findByEventoIdAndParticipanteCorreo(@Param("eventoId") Long eventoId,
            @Param("correo") String correo);

    @Query("""
                SELECT ep
                FROM EventoProgramacion ep
                JOIN ep.evento e
                WHERE e.eventoId = :eventoId
            """)
    public List<EventoProgramacion> findByEventoId(@Param("eventoId") Long eventoId);

    public void deleteByEvento(Evento evento);
}
