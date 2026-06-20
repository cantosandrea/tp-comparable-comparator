package ar.edu.unlar.prog3.tp_comparable_comparator.exception;

public class CriterioInvalidoException extends RuntimeException {
    public CriterioInvalidoException(String criterio) {
        super(criterio);
    }
}