package com.daw135.dawFinalProyect.controller.participante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daw135.dawFinalProyect.service.eventos.EventoService;


@Controller
@RequestMapping("/participante")
public class ParticipanteController {

    // private static final Logger logger = LogManager.getLogger(ParticipanteController.class);

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
    

}
