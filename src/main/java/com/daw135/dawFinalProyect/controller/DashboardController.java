package com.daw135.dawFinalProyect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daw135.dawFinalProyect.dto.eventos.EventoDTO;
import com.daw135.dawFinalProyect.enums.EstadoEnum;
import com.daw135.dawFinalProyect.service.eventos.EventoService;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private EventoService eventoService;

    @RequestMapping({"", "/"})
    public String obtenerVistaDashboard(Model model) {
        List<EventoDTO> eventos = eventoService.findAll();

        long eventosActivos = eventos.stream()
                .filter(e -> (e.getEstadoId().contentEquals(EstadoEnum.Activo.getCodigo()))).count();
        Long eventosFinalizados = eventos.stream()
                .filter(e -> (e.getEstadoId().contentEquals(EstadoEnum.Finalizado.getCodigo()))).count();
        long eventosCancelados = eventos.stream()
                .filter(e -> (e.getEstadoId().contentEquals(EstadoEnum.Cancelado.getCodigo()))).count();

        model.addAttribute("listadoEventos", eventos);
        model.addAttribute("eventosTotales", eventos.size());
        model.addAttribute("eventosActivos", eventosActivos);
        model.addAttribute("eventosFinalizados", eventosFinalizados);
        model.addAttribute("eventosCancelados", eventosCancelados);
        return "pages/dashboard/dashboard";
    }
    
}
