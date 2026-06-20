package ar.edu.unlar.prog3.tp_comparable_comparator.service;

import ar.edu.unlar.prog3.tp_comparable_comparator.model.Estudiante;
import ar.edu.unlar.prog3.tp_comparable_comparator.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class EstudianteService {

    private final EstudianteRepository repository;

    private final Map<String, Comparator<Estudiante>> comparadores = Map.of(
        "promedio",          Comparator.comparingDouble(Estudiante::getPromedio),
        "edad",              Comparator.comparingInt(Estudiante::getEdad),
        "nombre",            Comparator.comparing(Estudiante::getNombre),
        "materiasAprobadas", Comparator.comparingInt(Estudiante::getCantidadMateriasAprobadas),
        "legajo",            Comparator.comparing(Estudiante::getLegajo)
    );

    public EstudianteService(EstudianteRepository repository) {
        this.repository = repository;
    }

    public List<Estudiante> ordenar(String sortBy, String order) {
        Comparator<Estudiante> comparador = comparadores.get(sortBy);

        if (comparador == null) {
            throw new IllegalArgumentException(sortBy);
        }

        if ("desc".equalsIgnoreCase(order)) {
            comparador = comparador.reversed();
        }

        // Desempate por legajo
        comparador = comparador.thenComparing(Estudiante::getLegajo);

        List<Estudiante> lista = repository.findAll();
        lista.sort(comparador);
        return lista;
    }

    public List<String> getCriteriosAceptados() {
        return List.copyOf(comparadores.keySet());
    }
}