package com.daw135.dawFinalProyect.controller.participante;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daw135.dawFinalProyect.config.auth.AuthUtils;
import com.daw135.dawFinalProyect.dto.eventos.EventoDTO;
import com.daw135.dawFinalProyect.dto.eventos.EventoEvaluacionDTO;
import com.daw135.dawFinalProyect.helpers.DawUtil;
import com.daw135.dawFinalProyect.service.eventos.EventoService;

@Controller
@RequestMapping("/participante")
public class ParticipanteController {

    private static final Logger logger = LogManager.getLogger(ParticipanteController.class);

    @Autowired
    private EventoService eventoService;

    @GetMapping("/misEventos")
    public String misEventos(Model model) {
        model.addAttribute("eventosList", eventoService.findAllMisEventos());
        return "pages/participante/misEventos/misEventos";
    }

    @GetMapping("/detalleEvento/{id}")
    public String detalleEvento(@PathVariable Long id, Model model) {
        EventoDTO evento = new EventoDTO();
        if (AuthUtils.hasRole(DawUtil.ROLE_ADMIN) || AuthUtils.hasRole(DawUtil.ROLE_PONENTE)) {
            evento = eventoService.obtenerEventoInformacionByEventoId(id);
        } else {
            evento = eventoService.obtenerEventoInformacionByEventoIdAndCorreo(id);
        }

        model.addAttribute("evento", evento);
        return "pages/participante/misEventos/detalleEvento";
    }

    @PostMapping("/marcarAsistencia/{eventoRegistroId}")
    public ResponseEntity<String> marcarAsistencia(@PathVariable Long eventoRegistroId) {
        try {
            boolean marcado = eventoService.marcarAsistencia(eventoRegistroId);
            if (!marcado) {
                return ResponseEntity.badRequest().body("Ocurrio un error al marcar asistencia");
            }
            return ResponseEntity.ok("Asistencia marcada con exito");
        } catch (Exception e) {
            logger.error("Error al marcar asistencia", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrio un error al marcar asistencia");
        }
    }

    @PostMapping("/marcarAsistenciaAdm/{eventoRegistroId}")
    public ResponseEntity<Map<String, Object>> marcarAsistenciaAdm(@PathVariable Long eventoRegistroId,
            @RequestBody Map<String, Object> request) {
        try {
            boolean nuevoEstado = (boolean) request.get("estado");

            boolean marcado = eventoService.marcarAsistenciaAdm(eventoRegistroId, nuevoEstado);
            if (!marcado) {
                return ResponseEntity.badRequest()
                        .body(Map.of("success", false, "message", "Ocurrió un error al marcar asistencia"));
            }

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "asistenciaConfirmada", nuevoEstado));
        } catch (Exception e) {
            logger.error("Error al marcar asistencia", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "Ocurrió un error al marcar asistencia"));
        }
    }

    @GetMapping("/evaluaciones/{eventoId}")
    public ResponseEntity<List<EventoEvaluacionDTO>> obtenerEvaluaciones(@PathVariable Long eventoId) {
        List<EventoEvaluacionDTO> eventoEvaluacionDTOs = eventoService.findEvaluacionesByEventoId(eventoId);
        return ResponseEntity.ok(eventoEvaluacionDTOs);
    }

    @PostMapping("/evaluacion/guardar")
    public ResponseEntity<EventoEvaluacionDTO> guardarEvaluacion(@RequestBody EventoEvaluacionDTO eventoEvaluacionDto) {
        try {
            EventoEvaluacionDTO eventoEvaluacion = eventoService.guardarEvaluacion(eventoEvaluacionDto);
            return ResponseEntity.ok(eventoEvaluacion);
        } catch (Exception e) {
            logger.error("Error al guardar evaluacion", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new EventoEvaluacionDTO());
        }
    }

}
