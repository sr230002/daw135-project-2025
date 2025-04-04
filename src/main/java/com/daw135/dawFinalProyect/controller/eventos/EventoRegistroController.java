package com.daw135.dawFinalProyect.controller.eventos;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.daw135.dawFinalProyect.dto.eventos.EventoRegistroDTO;
import com.daw135.dawFinalProyect.service.admin.UsuarioService;
import com.daw135.dawFinalProyect.service.eventos.EventoProgramacionService;
import com.daw135.dawFinalProyect.service.eventos.EventoRegistroService;
import com.daw135.dawFinalProyect.service.eventos.EventoService;

@Controller
@RequestMapping("/registros")
public class EventoRegistroController {

    private static final Logger logger = LogManager.getLogger(EventoRegistroController.class);

    @Autowired
    private EventoRegistroService service;

    @Autowired
    private UsuarioService serviceUser;

    @Autowired
    private EventoService serviceEvent;

    @Autowired
    private EventoProgramacionService serviceSesion;

    @GetMapping({ "", "/" })
    public String obtenerVistaEvento(Model model) {
        List<EventoRegistroDTO> eventos = service.listarTodos();
        model.addAttribute("listadoRegistros", eventos);
        model.addAttribute("participante", new EventoRegistroDTO());
        model.addAttribute("listadoParticipantes", serviceUser.listarTodos());
        model.addAttribute("listadoEventos", serviceEvent.findAll());
        model.addAttribute("listadoSesiones", serviceSesion.listarTodos());

        return "pages/participante/participante";
    }

    @PostMapping("/guardar")
    public String guardarInscripcion(@ModelAttribute("participante") EventoRegistroDTO dto) {
        try {
            service.guardarInscripcion(dto);
        } catch (Exception e) {
            logger.error("Error al guardar evento", e);
        }
        return "redirect:/registros";
    }

    @GetMapping("/ver/{registroId}")
    @ResponseBody
    public ResponseEntity<EventoRegistroDTO> obtenerEvento(@PathVariable Long registroId) {
        EventoRegistroDTO evento = service.obtenerPorId(registroId);
        return ResponseEntity.ok(evento);
    }

    @PostMapping("/editar")
    public String editarEvento(@ModelAttribute("evento") EventoRegistroDTO dto) {
        try {
            service.editarInscripcion(dto);
        } catch (Exception e) {
            logger.error("Error al editar evento", e);
        }
        return "redirect:/registros";
    }

    @GetMapping("/eliminar/{registroId}")
    public String eliminarEvento(@PathVariable Long registroId, RedirectAttributes redirectAttributes) {
        String mensaje = service.eliminarInscripcion(registroId);
        redirectAttributes.addFlashAttribute("msj", mensaje);
        return "redirect:/registros";
    }

}
