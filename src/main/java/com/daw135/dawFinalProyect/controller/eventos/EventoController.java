package com.daw135.dawFinalProyect.controller.eventos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.daw135.dawFinalProyect.dto.eventos.EventoDTO;
import com.daw135.dawFinalProyect.entity.eventos.Evento;
import com.daw135.dawFinalProyect.enums.EstadoEnum;
import com.daw135.dawFinalProyect.service.eventos.EventoService;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping({ "", "/" })
    public String obtenerVistaEvento(Model model) {
        List<EventoDTO> eventos = eventoService.findAll();
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
        return "pages/evento/evento";
    }

    @PostMapping("/guardar")
    public String guardarEvento(@ModelAttribute("evento") EventoDTO eventoDto) {

        return "redirect:/eventos";
    }

}
