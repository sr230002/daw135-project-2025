package com.daw135.dawFinalProyect.controller.eventos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @GetMapping({"", "/"})
    public String obtenerVistaEvento() {
        return "pages/evento/evento";
    }

}
