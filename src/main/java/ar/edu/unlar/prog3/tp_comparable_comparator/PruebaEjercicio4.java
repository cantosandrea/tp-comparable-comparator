package ar.edu.unlar.prog3.tp_comparable_comparator;

import ar.edu.unlar.prog3.tp_comparable_comparator.model.Estudiante;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PruebaEjercicio4 {

    // Parte 3: Comparadores con lambda, Comparator.comparing() y referencias de método
    public static void main(String[] args) {

        List<Estudiante> lista = new ArrayList<>();
        lista.add(new Estudiante("LU-2024-001", "Martín Quiroga",     8.5, 22, 18));
        lista.add(new Estudiante("LU-2024-002", "Valeria Díaz",       8.5, 20, 15));
        lista.add(new Estudiante("LU-2024-003", "Facundo Castro",     7.2, 24, 22));
        lista.add(new Estudiante("LU-2024-004", "Camila Torres",      9.1, 21, 24));
        lista.add(new Estudiante("LU-2024-005", "Lucas González",     9.1, 23, 24));

        // 1. Lambda: por cantidadMateriasAprobadas ascendente
        Comparator<Estudiante> porMaterias =
            (e1, e2) -> Integer.compare(e1.getCantidadMateriasAprobadas(), e2.getCantidadMateriasAprobadas());

        lista.sort(porMaterias);
        System.out.println("================ Por materias aprobadas (ASC) ================");
        lista.forEach(System.out::println);

        // 2. Comparator.comparing() + referencia de método: por nombre alfabético
        Comparator<Estudiante> porNombre = Comparator.comparing(Estudiante::getNombre);

        lista.sort(porNombre);
        System.out.println("\n================ Por nombre (alfabetico) ================");
        lista.forEach(System.out::println);

        // 3. Comparator.comparing() + referencia de método: por edad ascendente
        Comparator<Estudiante> porEdad = Comparator.comparing(Estudiante::getEdad);

        lista.sort(porEdad);
        System.out.println("\n================ Por edad (ASC) ================");
        lista.forEach(System.out::println);
    }
}