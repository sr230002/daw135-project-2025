package com.daw135.dawFinalProyect.controller.participante;

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
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("evento", eventoService.obtenerEventoInformacionByEventoIdAndCorreo(id));
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrio un error al marcar asistencia");
        }
    }

}
