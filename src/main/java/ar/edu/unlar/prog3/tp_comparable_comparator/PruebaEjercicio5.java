package ar.edu.unlar.prog3.tp_comparable_comparator;

import ar.edu.unlar.prog3.tp_comparable_comparator.model.Estudiante;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PruebaEjercicio5 {
    public static void main(String[] args) {

        List<Estudiante> lista = new ArrayList<>();
        lista.add(new Estudiante("LU-2024-001", "Martin Quiroga",  8.5, 22, 18));
        lista.add(new Estudiante("LU-2024-002", "Valeria Diaz",    8.5, 20, 15));
        lista.add(new Estudiante("LU-2024-003", "Facundo Castro",  7.2, 24, 22));
        lista.add(new Estudiante("LU-2024-004", "Camila Torres",   9.1, 21, 24));
        lista.add(new Estudiante("LU-2024-005", "Lucas Gonzalez",  9.1, 23, 24));

        // 1. Promedio DESC, desempate por nombre ASC
        Comparator<Estudiante> porPromedioDesc_NombreAsc =
            Comparator.comparingDouble(Estudiante::getPromedio)
                      .reversed()
                      .thenComparing(Estudiante::getNombre);

        lista.sort(porPromedioDesc_NombreAsc);
        System.out.println("=== Promedio DESC, desempate por nombre ASC ===");
        lista.forEach(System.out::println);

        // 2. Promedio ASC usando reversed() sobre el comparador anterior
        Comparator<Estudiante> porPromedioAsc =
            Comparator.comparingDouble(Estudiante::getPromedio);

        lista.sort(porPromedioAsc);
        System.out.println("\n=== Promedio ASC (con reversed del DESC) ===");
        lista.forEach(System.out::println);

        // 3. Materias aprobadas DESC, desempate por nombre ASC
        Comparator<Estudiante> porMateriasDesc_NombreAsc =
            Comparator.comparingInt(Estudiante::getCantidadMateriasAprobadas)
                      .reversed()
                      .thenComparing(Estudiante::getNombre);

        lista.sort(porMateriasDesc_NombreAsc);
        System.out.println("\n=== Materias aprobadas DESC, desempate por nombre ASC ===");
        lista.forEach(System.out::println);
    }
}