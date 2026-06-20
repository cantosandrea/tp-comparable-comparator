package ar.edu.unlar.prog3.tp_comparable_comparator;

import ar.edu.unlar.prog3.tp_comparable_comparator.model.Estudiante;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PruebaEjercicio1 {
    public static void main(String[] args) {
    
        List<Estudiante> lista = new ArrayList<>();
        lista.add(new Estudiante("LU-2024-001", "Martin Quiroga", 8.5, 22, 18));
        lista.add(new Estudiante("LU-2024-002", "Valeria Diaz", 8.5, 20, 15));
        lista.add(new Estudiante("LU-2024-003", "Facundo Castro", 7.2, 24, 22));
        lista.add(new Estudiante("LU-2024-004", "Camila Torres", 9.1, 21, 24));
        lista.add(new Estudiante("LU-2024-005", "Lucas Gonzalez", 9.1, 23, 24));

       System.out.println("LISTA ANTES DE ORDENAR: ");
        lista.forEach(System.out::println);

   Collections.sort(lista);

        /* =================================================================================
        ERROR
        "The method sort(List<T>) in the type Collections is not applicable for the arguments (List<Estudiante>)"
        */

    
        System.out.println("\nLISTA DESPUÉS DE ORDENAR: ");
        lista.forEach(System.out::println); 
    }
}