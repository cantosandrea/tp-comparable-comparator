package ar.edu.unlar.prog3.tp_comparable_comparator.exception;

import ar.edu.unlar.prog3.tp_comparable_comparator.service.EstudianteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final EstudianteService service;

    public GlobalExceptionHandler(EstudianteService service) {
        this.service = service;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleCriterioInvalido(IllegalArgumentException ex) {
        List<String> criteriosAceptados = service.getCriteriosAceptados();

        Map<String, Object> error = Map.of(
            "error", "Criterio de ordenamiento no válido",
            "criterioRecibido", ex.getMessage(),
            "criteriosAceptados", criteriosAceptados
        );

        return ResponseEntity.badRequest().body(error);
    }
}