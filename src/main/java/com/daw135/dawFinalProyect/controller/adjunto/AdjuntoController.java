package com.daw135.dawFinalProyect.controller.adjunto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.daw135.dawFinalProyect.dto.adjunto.EventoAdjuntoDTO;
import com.daw135.dawFinalProyect.service.adjunto.AdjuntoService;

@Controller
@RequestMapping("/adjuntos")
public class AdjuntoController {

    private static final Logger logger = LogManager.getLogger(AdjuntoController.class);

    @Autowired
    private AdjuntoService adjuntoService;

    @PostMapping(value = "/evento/subir", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> cargarAdjunto(
            @RequestParam("eventoId") Long eventoId,
            @RequestParam("file") MultipartFile file,
            @RequestParam(required = false) String descripcion,
            @RequestParam(defaultValue = "true") boolean visible) {

        try {
            EventoAdjuntoDTO adjunto = adjuntoService.cargarEventoAdjunto(file, eventoId, descripcion, visible);
            return ResponseEntity.ok(adjunto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir adjunto: " + e.getMessage());
        }
    }

    @DeleteMapping("/evento/eliminar/{eventoAdjuntoId}")
    public ResponseEntity<String> eliminarAdjunto(@PathVariable Long eventoAdjuntoId) {
        try {
            boolean eliminado = adjuntoService.eliminarEventoAdjunto(eventoAdjuntoId);
            if (eliminado) {
                return ResponseEntity.ok("Adjunto eliminado con éxito");
            }
            return ResponseEntity.badRequest().body("Ocurrio un error al eliminar adjunto");
        } catch (Exception e) {
            logger.error("Error al eliminar adjunto", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrio un error al eliminar adjunto");
        }
    }

}
