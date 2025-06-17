package com.daw135.dawFinalProyect.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daw135.dawFinalProyect.controller.participante.ParticipanteController;
import com.daw135.dawFinalProyect.service.eventos.EventoService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

        private static final Logger logger = LogManager.getLogger(ParticipanteController.class);

        @Autowired
        private EventoService eventoService;

        @RequestMapping({ "", "/" })
        public String obtenerVistaDashboard(Model model) {
                logger.info("Obteniendo vista de dashboard");
                model.addAttribute("eventosList", eventoService.findEventosDisponibles());
                return "pages/dashboard/dashboard";
        }

}
