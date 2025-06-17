package com.daw135.dawFinalProyect.serviceImpl.adjunto;

import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.daw135.dawFinalProyect.dto.adjunto.CloudinaryUploadResult;
import com.daw135.dawFinalProyect.entity.adjunto.Adjunto;
import com.daw135.dawFinalProyect.helpers.CloudinaryService;
import com.daw135.dawFinalProyect.repository.adjunto.AdjuntoRepository;
import com.daw135.dawFinalProyect.service.adjunto.AdjuntoService;

import jakarta.transaction.Transactional;

@Service
public class AdjuntoServiceImpl implements AdjuntoService {
    private static final Logger logger = LogManager.getLogger(AdjuntoServiceImpl.class);

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private AdjuntoRepository adjuntoRepository;

    @Override
    @Transactional
    public Optional<Adjunto> uploadFile(MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                logger.warn("No se puede subir un archivo vacio");
                return Optional.empty();
            }

            CloudinaryUploadResult uploadResult = cloudinaryService.uploadFile(file);

            Adjunto adjunto = new Adjunto();
            adjunto.setUrl(uploadResult.getSecureUrl());
            adjunto.setPublicId(uploadResult.getPublicId());
            adjunto.setFormato(uploadResult.getFormato());
            adjunto.setTipoArchivo(uploadResult.getTipoArchivo());
            adjunto.setTamano(uploadResult.getTamaño());
            adjunto.setNombreOriginal(uploadResult.getNombreOriginal());

            Adjunto adjuntoGuardado = adjuntoRepository.save(adjunto);

            return Optional.of(adjuntoGuardado);
        } catch (IOException e) {
            logger.error("Error al subir archivo a Cloudinary", e);
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Error inesperado al subir archivo", e);
            return Optional.empty();
        }

    }

    @Override
    @Transactional
    public boolean deleteFile(Long adjuntoId) {
        try {
            Optional<Adjunto> findAdjunto = adjuntoRepository.findById(adjuntoId);
            if (findAdjunto.isEmpty()) {
                logger.warn("Adjunto no encontrado");
                return false;
            }
            Adjunto adjunto = findAdjunto.get();
            String resourceType = adjunto.getTipoArchivo().equals("imagen") ? "image" : "raw";
            cloudinaryService.deleteFile(adjunto.getPublicId(), resourceType);
            adjuntoRepository.delete(adjunto);
            return true;

        } catch (IOException e) {
            logger.error("Error al eliminar archivo de Cloudinary", e);
            throw new RuntimeException("Error al eliminar archivo", e);
        } catch (Exception e) {
            logger.error("Error inesperado al eliminar archivo", e);
            throw new RuntimeException("Error inesperado", e);
        }
    }
}
