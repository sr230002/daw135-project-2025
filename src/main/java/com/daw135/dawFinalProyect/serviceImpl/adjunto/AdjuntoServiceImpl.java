package com.daw135.dawFinalProyect.serviceImpl.adjunto;

import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.daw135.dawFinalProyect.dto.adjunto.CloudinaryUploadResult;
import com.daw135.dawFinalProyect.dto.adjunto.EventoAdjuntoDTO;
import com.daw135.dawFinalProyect.entity.adjunto.Adjunto;
import com.daw135.dawFinalProyect.entity.eventos.Evento;
import com.daw135.dawFinalProyect.entity.eventos.EventoAdjunto;
import com.daw135.dawFinalProyect.helpers.CloudinaryService;
import com.daw135.dawFinalProyect.mapper.adjunto.AdjuntoMapper;
import com.daw135.dawFinalProyect.repository.adjunto.AdjuntoRepository;
import com.daw135.dawFinalProyect.repository.eventos.EventoAdjuntoRepository;
import com.daw135.dawFinalProyect.repository.eventos.EventoRepository;
import com.daw135.dawFinalProyect.service.adjunto.AdjuntoService;

import jakarta.transaction.Transactional;

@Service
public class AdjuntoServiceImpl implements AdjuntoService {
    private static final Logger logger = LogManager.getLogger(AdjuntoServiceImpl.class);

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private AdjuntoRepository adjuntoRepository;

    @Autowired
    private EventoAdjuntoRepository eventoAdjuntoRepo;

    @Autowired
    private EventoRepository eventoRepo;

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
            adjunto.setTamano(uploadResult.getTamano());
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

    @Override
    @Transactional
    public EventoAdjuntoDTO cargarEventoAdjunto(MultipartFile file, Long eventoId, String descripcion,
            boolean visible) {
        try {
            Optional<Adjunto> adjunto = uploadFile(file);
            if (adjunto.isEmpty()) {
                logger.error("No se pudo subir el archivo adjunto");
                return null;
            }

            Evento evento = eventoRepo.findById(eventoId).orElse(null);

            EventoAdjunto eventoAdjunto = new EventoAdjunto();
            eventoAdjunto.setAdjunto(adjunto.get());
            eventoAdjunto.setEvento(evento);
            eventoAdjunto.setDescripcion(descripcion);
            eventoAdjunto.setVisible(visible);

            return AdjuntoMapper.INSTANCE.toEventoAdjuntoDTO(eventoAdjuntoRepo.save(eventoAdjunto));

        } catch (Exception e) {
            logger.error("Error al subir adjunto", e);
            return null;
        }
    }

    @Override
    @Transactional
    public boolean eliminarEventoAdjunto(Long eventoAdjuntoId) {
        try {
            Optional<EventoAdjunto> findAdjunto = eventoAdjuntoRepo.findById(eventoAdjuntoId);
            if (findAdjunto.isEmpty()) {
                logger.warn("Adjunto no encontrado");
                return false;
            }
            EventoAdjunto eventoAdjunto = findAdjunto.get();
            String resourceType = eventoAdjunto.getAdjunto().getTipoArchivo().equals("imagen") ? "image" : "raw";
            cloudinaryService.deleteFile(eventoAdjunto.getAdjunto().getPublicId(), resourceType);
            eventoAdjuntoRepo.delete(eventoAdjunto);
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
