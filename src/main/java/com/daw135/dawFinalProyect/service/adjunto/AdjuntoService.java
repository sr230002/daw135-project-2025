package com.daw135.dawFinalProyect.service.adjunto;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.daw135.dawFinalProyect.entity.adjunto.Adjunto;

public interface AdjuntoService {

    public Optional<Adjunto> uploadFile(MultipartFile file);
    public boolean deleteFile(Long adjuntoId);

}
