# RESPUESTAS.md — TP Comparable y Comparator

**Repositorio:** https://github.com/cantosandrea/tp-comparable-comparator

**Integrantes:**
- Aucachi Ortega, Rocio Valeria EISI1467 _ 44431436
- Cantos Luciana Andrea EISI1468

 ## Commit Parte 1 — El problema

# Pregunta 1: ¿Por qué Collections.sort() no compila cuando le pasamos una List<Estudiante>? ¿Qué contrato exige Java que nuestra clase no está cumpliendo?

No compila porque Collections.sort() necesita que los objetos de la lista implementen la interfaz Comparable. Como Estudiante es una clase propia, Java no sabe cómo ordenarla — no sabe si ir por nombre, promedio o edad. El contrato que no se cumple es el método compareTo(T o), que es el que define el orden natural de una clase.

## Commit Parte 2 — Comparable: el orden natural

# Pregunta 2: ¿Por qué elegiste el atributo promedio como orden natural? ¿Qué pasaría si mañana un nuevo requisito pide ordenar por cantidadMateriasAprobadas? ¿Modificarías compareTo? ¿Qué consecuencias tendría?

En un contexto universitario es la métrica mas representativa del rendimiento academico, el criterio más obvio para ordenar estudiantes.
Si mañana aparece un requisito nuevo para ordenar por cantidadMateriasAprobadas, modificar compareTo() sería un problema: estaríamos cambiando el orden natural de la clase, lo que puede romper código que ya dependía de ese orden. Ademas, Comparable solo permite un único criterio, así que tendríamos que elegir uno y perder el otro. La solución correcta en ese caso sería usar Comparator, que permite definir múltiples criterios sin tocar la clase.

## Commit Parte 3 — Comparator: estrategias externas

# Pregunta 3: Comparable nos ata a un único criterio de ordenamiento. ¿Qué problemas de diseño introduce esto si nuestro sistema necesitara ordenar la misma lista de estudiantes de 4 formas distintas según el contexto? Relacioná tu respuesta con los principios de responsabilidad única (SRP) y abierto/cerrado (OCP).

Si necesitáramos ordenar la lista de 4 formas distintas usando solo Comparable, tendríamos un problema grande: solo permite definir un único orden dentro de la clase. Cada vez que aparezca un criterio nuevo, habría que entrar a modificar Estudiante directamente, lo cual no es una buena práctica.

Por un lado, la clase Estudiante debería ocuparse de representar un estudiante, no de decidir cómo se ordena en cada contexto posible. Por otro lado, un buen diseño debería permitirnos agregar nuevos criterios sin tener que tocar código que ya funciona.
La solución es Comparator, que nos permite crear cada criterio de ordenamiento por separado, sin modificar la clase Estudiante para nada.

## Commit Parte 4 — Integración con Spring Boot 

# Pregunta 4: Explicá con tus palabras qué es un overflow de enteros, por qué el "truco de la resta" lo provoca, qué parte del contrato de Comparatorrompe, y por qué Integer.compare()no sufre este problema.

Un overflow de enteros ocurre cuando el resultado de una operación matemática supera el valor máximo que puede almacenar un int (que es 2.147.483.647). Cuando eso pasa, Java no da error sino que el número "da la vuelta" y se convierte en un valor completamente incorrecto.

El "truco de la resta" provoca exactamente eso. Si restamos la edad de dos estudiantes donde uno tiene Integer.MAX_VALUE y el otro -1, el resultado debería ser un número positivo enorme, pero como no cabe en un int, termina siendo un número negativo. Eso hace que el comparador devuelva un resultado incorrecto, violando el contrato de Comparator que dice que si A es mayor que B, el resultado tiene que ser positivo.

Integer.compare() no tiene este problema porque no hace ninguna resta. Internamente compara los valores directamente y devuelve -1, 0 o 1 según corresponda, sin ningún riesgo de overflow.


# Pregunta 5: ¿Qué patrón de diseño estás aplicando al usar un Map<String, Comparator<T>>en lugar de un switch? Explicá cómo se relaciona este patrón con el polimorfismo y por qué es preferible a la alternativa procesal.

Al usar un Map<String, Comparator<Estudiante>> en lugar de un switch, estamos aplicando el patrón Strategy. La idea es simple: en vez de tener un bloque de código que pregunta "¿qué criterio llegó?" y hace cosas distintas para cada caso, el Map simplemente busca el comparador que corresponde y lo usa directamente.
Esto se relaciona con el polimorfismo porque todos los comparadores funcionan de la misma manera desde afuera, pero cada uno ordena distinto por dentro. El servicio no necesita saber los detalles de cada uno, solo los llama.
La ventaja sobre el switch es que si el día de mañana queremos agregar un nuevo criterio, solo agregamos una línea al Map. No hay que tocar nada más, y el resto del código sigue funcionando igual.