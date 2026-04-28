public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor; // to base (kg)

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    // Convert to base unit (kg)
    public double toBase(double value) {
        return value * conversionFactor;
    }

    // Convert from base unit (kg)
    public double fromBase(double baseValue) {
        return baseValue / conversionFactor;
    }
}