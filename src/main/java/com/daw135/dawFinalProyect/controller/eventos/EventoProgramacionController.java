package com.daw135.dawFinalProyect.controller.eventos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.daw135.dawFinalProyect.dto.admin.EventoProgramacionDTO;
import com.daw135.dawFinalProyect.service.eventos.EventoProgramacionService;

@RestController
@RequestMapping("/eventos/programacion")
public class EventoProgramacionController {

    @Autowired
    private EventoProgramacionService eventoProgramacionService;

    @GetMapping
    public List<EventoProgramacionDTO> listarTodos() {
        return eventoProgramacionService.listarTodos();
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
}
