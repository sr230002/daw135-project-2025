package com.daw135.dawFinalProyect.repository.adjunto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw135.dawFinalProyect.entity.adjunto.Adjunto;

@Repository
public interface AdjuntoRepository extends JpaRepository<Adjunto, Long> {

}
