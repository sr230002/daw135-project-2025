package com.daw135.dawFinalProyect.repository.eventos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daw135.dawFinalProyect.entity.admin.EventoProgramacion;
import com.daw135.dawFinalProyect.entity.eventos.EventoRegistro;

@Repository
public interface EventoRegistroRepository extends JpaRepository<EventoRegistro, Long> {

    public List<EventoRegistro> findBySesion(EventoProgramacion eventoProgramacion);

    @Query("""
                SELECT r
                FROM EventoRegistro r
                JOIN r.sesion s
                JOIN r.participanteId p
                WHERE s.eventoProgramacionId = :sesionId
                  AND LOWER(p.correo) = LOWER(:correo)
            """)
    public List<EventoRegistro> findBySesionIdAndParticipanteCorreo(@Param("sesionId") Long sesionId,
            @Param("correo") String correo);

}
