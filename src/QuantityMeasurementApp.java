import java.util.*;

public class QuantityMeasurementApp {

    // Base unit = FEET
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    // Quantity class
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid numeric value");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        // Instance conversion
        public Quantity convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            double feetValue = this.toFeet();
            double converted = targetUnit.fromFeet(feetValue);
            return new Quantity(converted, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            return Math.abs(this.toFeet() - other.toFeet()) < 1e-6;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // ✅ Static API (main UC5 requirement)
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }

        // Normalize to base (feet)
        double feetValue = source.toFeet(value);

        // Convert to target
        return target.fromFeet(feetValue);
    }

    // 🔹 Demo methods (overloading)
    public static void demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
        double result = convert(value, from, to);
        System.out.println("convert(" + value + ", " + from + ", " + to + ") = " + result);
    }

    public static void demonstrateLengthConversion(Quantity q, LengthUnit to) {
        Quantity result = q.convertTo(to);
        System.out.println(q + " -> " + result);
    }

    // Main method
    public static void main(String[] args) {

        // Static API tests
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCH);     // 12
        demonstrateLengthConversion(3.0, LengthUnit.YARD, LengthUnit.FEET);     // 9
        demonstrateLengthConversion(36.0, LengthUnit.INCH, LengthUnit.YARD);    // 1
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH); // ~0.393701

        // Instance method test
        Quantity q = new Quantity(1.0, LengthUnit.YARD);
        demonstrateLengthConversion(q, LengthUnit.INCH); // 36

        // Equality check
        System.out.println(new Quantity(1, LengthUnit.FEET)
                .equals(new Quantity(12, LengthUnit.INCH))); // true
    }
}