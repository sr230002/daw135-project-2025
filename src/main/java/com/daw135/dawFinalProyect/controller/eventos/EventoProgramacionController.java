package com.daw135.dawFinalProyect.controller.eventos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daw135.dawFinalProyect.dto.eventos.EventoProgramacionDTO;
import com.daw135.dawFinalProyect.service.eventos.EventoProgramacionService;

@Controller
@RequestMapping("/sesiones")
public class EventoProgramacionController {

    @Autowired
    private EventoProgramacionService eventoProgramacionService;

    @GetMapping({ "", "/" })
    public String obtenerVistaEvento(Model model) {
        return "pages/sesion/sesion";
    }

    @PostMapping
    public ResponseEntity<String> guardarEventoProgramacion(@RequestBody EventoProgramacionDTO dto) {
        try {
            String mensaje = eventoProgramacionService.guardarEventoProgramacion(dto);
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar el evento programado: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> editarEventoProgramacion(@RequestBody EventoProgramacionDTO dto) {
        try {
            String mensaje = eventoProgramacionService.editarEventoProgramacion(dto);
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al editar el evento programado: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEventoProgramacion(@PathVariable Long id) {
        String mensaje = eventoProgramacionService.eliminarEventoProgramacion(id);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/sesionesPorEventoCmb/{id}")
    @ResponseBody
    public ResponseEntity<List<EventoProgramacionDTO>> obtenerSesionesPorEventoId(@PathVariable Long id) {
        List<EventoProgramacionDTO> listSesiones = eventoProgramacionService.obtenerSesionesPorEventoId(id);
        return ResponseEntity.ok(listSesiones);
    }
}
