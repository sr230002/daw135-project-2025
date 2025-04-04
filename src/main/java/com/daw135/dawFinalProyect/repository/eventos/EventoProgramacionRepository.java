package com.daw135.dawFinalProyect.repository.eventos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw135.dawFinalProyect.entity.admin.EventoProgramacion;

@Repository
public interface EventoProgramacionRepository extends JpaRepository<EventoProgramacion, Long> {
    // Additional query methods can be added here if needed
}
