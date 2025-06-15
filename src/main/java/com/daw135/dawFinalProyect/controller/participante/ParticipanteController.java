package com.daw135.dawFinalProyect.controller.participante;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

}
