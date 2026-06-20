package ar.edu.unlar.prog3.tp_comparable_comparator;

import ar.edu.unlar.prog3.tp_comparable_comparator.model.Estudiante;
import java.util.Comparator;

public class PruebaEjercicio6 {
    public static void main(String[] args) {

        Estudiante e1 = new Estudiante("LU-2024-001", "Estudiante A", 8.0, Integer.MAX_VALUE, 10);
        Estudiante e2 = new Estudiante("LU-2024-002", "Estudiante B", 7.0, -1, 8);

        //INCORRECTO: antipatrón de la resta
        Comparator<Estudiante> restaTramposa = (a, b) -> a.getEdad() - b.getEdad();

        int resultadoMal = restaTramposa.compare(e1, e2);
        System.out.println("=== Con resta (INCORRECTO) ===");
        System.out.println("Comparando edad " + Integer.MAX_VALUE + " vs -1");
        System.out.println("Resultado esperado: positivo (e1 va después)");
        System.out.println("Resultado obtenido: " + resultadoMal);
        System.out.println("¿Es positivo? " + (resultadoMal > 0) + " ← debería ser true");

        System.out.println();

        //CORRECTO: con Integer.compare()
        Comparator<Estudiante> correcto = (a, b) -> Integer.compare(a.getEdad(), b.getEdad());

        int resultadoBien = correcto.compare(e1, e2);
        System.out.println("=== Con Integer.compare() (CORRECTO) ===");
        System.out.println("Comparando edad " + Integer.MAX_VALUE + " vs -1");
        System.out.println("Resultado esperado: positivo (e1 va después)");
        System.out.println("Resultado obtenido: " + resultadoBien);
        System.out.println("¿Es positivo? " + (resultadoBien > 0) + " ← debería ser true");
    }
}