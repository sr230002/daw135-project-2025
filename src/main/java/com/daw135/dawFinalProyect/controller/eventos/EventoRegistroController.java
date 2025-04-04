package com.daw135.dawFinalProyect.controller.eventos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daw135.dawFinalProyect.dto.eventos.EventoRegistroDTO;
import com.daw135.dawFinalProyect.service.eventos.EventoRegistroService;

@Controller
@RequestMapping("/registros")
public class EventoRegistroController {

    @Autowired
    private EventoRegistroService service;


    @GetMapping({ "", "/" })
    public String obtenerVistaEvento(Model model) {
        List<EventoRegistroDTO> eventos =service.listarTodos();
        model.addAttribute("listadoRegistros", eventos);

        return "pages/evento/evento";
    }

}
