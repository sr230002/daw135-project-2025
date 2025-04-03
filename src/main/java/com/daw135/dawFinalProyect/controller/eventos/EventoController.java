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

import com.daw135.dawFinalProyect.dto.admin.SedeDTO;
import com.daw135.dawFinalProyect.dto.eventos.EventoDTO;
import com.daw135.dawFinalProyect.dto.eventos.TipoEventoDTO;
import com.daw135.dawFinalProyect.enums.EstadoEnum;
import com.daw135.dawFinalProyect.service.admin.SedeService;
import com.daw135.dawFinalProyect.service.eventos.EventoService;
import com.daw135.dawFinalProyect.service.eventos.TipoEventoService;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    private static final Logger logger = LogManager.getLogger(EventoController.class);

    @Autowired
    private EventoService eventoService;

    @Autowired
    private TipoEventoService tipoEventoService;

    @Autowired
    private SedeService sedeService;

    @GetMapping({ "", "/" })
    public String obtenerVistaEvento(Model model) {
        List<EventoDTO> eventos = eventoService.findAll();
        List<TipoEventoDTO> listTiposEvento = tipoEventoService.findAll();
        List<SedeDTO> listSedes = sedeService.findAll();

        long eventosActivos = eventos.stream()
                .filter(e -> (e.getEstado().contentEquals(EstadoEnum.Activo.getCodigo()))).count();
        Long eventosFinalizados = eventos.stream()
                .filter(e -> (e.getEstado().contentEquals(EstadoEnum.Finalizado.getCodigo()))).count();
        long eventosCancelados = eventos.stream()
                .filter(e -> (e.getEstado().contentEquals(EstadoEnum.Cancelado.getCodigo()))).count();

        model.addAttribute("listadoEventos", eventos);
        model.addAttribute("eventosTotales", eventos.size());
        model.addAttribute("eventosActivos", eventosActivos);
        model.addAttribute("eventosFinalizados", eventosFinalizados);
        model.addAttribute("eventosCancelados", eventosCancelados);
        model.addAttribute("evento", new EventoDTO());
        model.addAttribute("listTiposEventos", listTiposEvento);
        model.addAttribute("listSedes", listSedes);

        return "pages/evento/evento";
    }

    @PostMapping("/guardar")
    public String guardarEvento(@ModelAttribute("evento") EventoDTO eventoDto) {
        try {
            eventoService.guardarEvento(eventoDto);
        } catch (Exception e) {
            logger.error("Error al guardar evento", e);
        }
        return "redirect:/eventos";
    }

    @PostMapping("/editar")
    public String editarEvento(@ModelAttribute("evento") EventoDTO eventoDto) {
        try {
            eventoService.editarEvento(eventoDto);
        } catch (Exception e) {
            logger.error("Error al editar evento", e);
        }
        return "redirect:/eventos";
    }

    @GetMapping("/ver/{id}")
    @ResponseBody
    public ResponseEntity<EventoDTO> obtenerEvento(@PathVariable Long id) {
        EventoDTO evento = eventoService.obtenerPorId(id);
        return ResponseEntity.ok(evento);
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEvento(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        String mensaje = eventoService.eliminarEvento(id);
        redirectAttributes.addFlashAttribute("msj", mensaje);
        return "redirect:/eventos";
    }

}
