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

import com.daw135.dawFinalProyect.dto.admin.UsuarioDTO;
import com.daw135.dawFinalProyect.dto.eventos.EventoDTO;
import com.daw135.dawFinalProyect.dto.eventos.EventoProgramacionDTO;
import com.daw135.dawFinalProyect.helpers.DawUtil;
import com.daw135.dawFinalProyect.service.admin.security.UsuarioService;
import com.daw135.dawFinalProyect.service.eventos.EventoProgramacionService;
import com.daw135.dawFinalProyect.service.eventos.EventoService;

@Controller
@RequestMapping("/sesiones")
public class EventoProgramacionController {

    private static final Logger logger = LogManager.getLogger(EventoProgramacionController.class);
    @Autowired
    private EventoProgramacionService eventoProgramacionService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping({ "", "/" })
    public String view(Model model) {
        List<EventoProgramacionDTO> listaEventoProgramacion = eventoProgramacionService.listarTodos();
        List<EventoDTO> listaEvento = eventoService.findAll();
        List<UsuarioDTO> listaUsuario = usuarioService.findUserByRol(DawUtil.ROLE_PONENTE);

        model.addAttribute("listadoEventos", listaEvento);
        model.addAttribute("listadoSesiones", listaEventoProgramacion);
        model.addAttribute("listadoPonentes", listaUsuario);
        return "pages/sesion/sesion";
    }

    @GetMapping("/ver/{id}")
    @ResponseBody
    public ResponseEntity<EventoProgramacionDTO> obtenerEventoProgramacion(@PathVariable Long id) {
        EventoProgramacionDTO eventoProgramacion = eventoProgramacionService.findById(id)
                .orElse(new EventoProgramacionDTO());
        return ResponseEntity.ok(eventoProgramacion);
    }

    @PostMapping("/guardar")
    public String guardarEventoProgramacion(@ModelAttribute("sesion") EventoProgramacionDTO dto) {
        try {
            eventoProgramacionService.guardarEventoProgramacion(dto);
        } catch (Exception e) {
            logger.error("Error al guardar evento programado", e);
        }
        return "redirect:/sesiones";
    }

    @PostMapping("/editar")
    public String editarEventoProgramacion(
            @ModelAttribute("sesion") EventoProgramacionDTO dto) {
        try {
            eventoProgramacionService.editarEventoProgramacion(dto);
        } catch (Exception e) {
            logger.error("Error al editar evento programado", e);
        }
        return "redirect:/sesiones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEventoProgramacion(@PathVariable Long id) {
        eventoProgramacionService.eliminarEventoProgramacion(id);
        return "redirect:/sesiones";
    }

    @GetMapping("/sesionesPorEventoCmb/{id}")
    @ResponseBody
    public ResponseEntity<List<EventoProgramacionDTO>> obtenerSesionesPorEventoId(@PathVariable Long id) {
        List<EventoProgramacionDTO> listSesiones = eventoProgramacionService.obtenerSesionesPorEventoId(id);
        return ResponseEntity.ok(listSesiones);
    }
}
