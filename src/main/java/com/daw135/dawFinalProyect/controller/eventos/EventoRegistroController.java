package com.daw135.dawFinalProyect.controller.eventos;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daw135.dawFinalProyect.dto.eventos.EventoRegistroDTO;
import com.daw135.dawFinalProyect.service.admin.UsuarioService;
import com.daw135.dawFinalProyect.service.eventos.EventoProgramacionService;
import com.daw135.dawFinalProyect.service.eventos.EventoRegistroService;
import com.daw135.dawFinalProyect.service.eventos.EventoService;

@Controller
@RequestMapping("/registros")
public class EventoRegistroController {

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
        List<EventoRegistroDTO> eventos =service.listarTodos();
        model.addAttribute("listadoRegistros", eventos);
        model.addAttribute("participante", new EventoRegistroDTO());
        model.addAttribute("listadoParticipantes", serviceUser.listarTodos());
        model.addAttribute("listadoEventos", serviceEvent.findAll());
        model.addAttribute("listadoSesiones", serviceSesion.listarTodos());

        return "pages/participante/participante";
    }

}
