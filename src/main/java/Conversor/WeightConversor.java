package Conversor;

public class WeightConversor {

    // Conversion factor: 1 pound = 453.59237 grams (exact, internationally defined)
    private static final double GRAMOS_POR_LIBRA = 453.59237;

    /**
     * Converts grams to pounds.
     * Formula: libras = gramos / 453.59237
     */
    public double convertirGramos_Libras(double gramos) {
        validarNumero(gramos, "gramos");
        validarNoNegativo(gramos, "gramos");
        return gramos / GRAMOS_POR_LIBRA;
    }

    /**
     * Converts pounds to grams.
     * Formula: gramos = libras * 453.59237
     */
    public double convertirLibras_Gramos(double libras) {
        validarNumero(libras, "libras");
        validarNoNegativo(libras, "libras");
        return libras * GRAMOS_POR_LIBRA;
    }

    // ---------- Helper validation methods ----------

    /**
     * Rejects NaN and Infinite values, which are not valid weights.
     */
    private void validarNumero(double valor, String unidad) {
        if (Double.isNaN(valor)) {
            throw new IllegalArgumentException("El valor en " + unidad + " no puede ser NaN.");
        }
        if (Double.isInfinite(valor)) {
            throw new IllegalArgumentException("El valor en " + unidad + " no puede ser infinito.");
        }
    }

    /**
     * Rejects negative weights, since mass cannot be negative.
     */
    private void validarNoNegativo(double valor, String unidad) {
        if (valor < 0) {
            throw new IllegalArgumentException(
                    "El valor en " + unidad + " no puede ser negativo (" + valor + ").");
        }
    }
}