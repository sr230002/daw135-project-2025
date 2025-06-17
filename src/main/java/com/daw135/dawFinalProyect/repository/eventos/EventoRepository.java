package com.daw135.dawFinalProyect.repository.eventos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daw135.dawFinalProyect.entity.eventos.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query("SELECT DISTINCT e FROM Evento e " +
            "JOIN e.programaciones p " +
            "JOIN p.registros r " +
            "WHERE r.participanteId.correo = :correo")
    List<Evento> findEventosByParticipanteCorreo(@Param("correo") String correo);

    @Query("""
            SELECT DISTINCT e FROM Evento e
            JOIN e.programaciones p
            JOIN p.registros r
            WHERE 
                r.participanteId.correo != :correo
                AND e.estado.estado = 'ACT'
    """)
    List<Evento> findEventosDisponibles(@Param("correo") String correo);

}
