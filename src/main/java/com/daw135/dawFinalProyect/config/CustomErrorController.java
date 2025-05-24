package com.daw135.dawFinalProyect.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.exceptions.TemplateInputException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@ControllerAdvice
public class CustomErrorController implements ErrorController {

    private static final Logger logger = LogManager.getLogger(CustomErrorController.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            
            // Manejo específico para 404
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                logger.warn("Recurso no encontrado: {}", request.getRequestURI());
                model.addAttribute("errorCode", statusCode);
                model.addAttribute("errorMessage", "El recurso solicitado no existe");
                return "pages/auth/404"; // Plantilla específica para 404
            }
            
            // Manejo genérico para otros errores
            logger.warn("Error {} en {}", statusCode, request.getRequestURI());
            model.addAttribute("errorCode", statusCode);
            model.addAttribute("errorMessage", "Ocurrió un error inesperado");
            return "pages/auth/error"; // Plantilla genérica de error
        }
        
        // Caso por defecto cuando no hay código de estado
        return "pages/auth/error";
    }

    @ExceptionHandler(TemplateInputException.class)
    public String handleTemplateError(TemplateInputException ex, Model model) {
        logger.warn("Error al procesar plantilla: {}", ex.getMessage());
        
        // Determinar si es un error 404 (template no encontrado)
        if (ex.getMessage() != null && ex.getMessage().contains("TemplateResolutionException")) {
            model.addAttribute("errorCode", 404);
            model.addAttribute("errorMessage", "Página no encontrada");
            return "pages/auth/404";
        }
        
        // Otros errores de plantilla
        model.addAttribute("errorCode", 500);
        model.addAttribute("errorMessage", "Error al procesar la página");
        return "pages/auth/error";
    }
}